package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;
import entities.recipe.factory.CocktailFactory;
import entities.recipe.factory.RecipeFactory;
import entities.user.User;
import entities.user.factory.CommonUserFactory;
import entities.user.factory.UserFactory;
import exceptions.RecipeNotFound;
import exceptions.UserNotFound;
import org.bson.Document;
import org.bson.types.ObjectId;
import use_case.bookmark_recipe.BookmarkRecipeDataAccessInterface;
import use_case.create_recipe.CustomRecipeDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;
import use_case.user_profile.UserProfileDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Data Access Object for users.
 */
public class UserDataAccessObject implements
        LoginDataAccessInterface,
        SignupDataAccessInterface,
        BookmarkRecipeDataAccessInterface,
        CustomRecipeDataAccessInterface,
        UserProfileDataAccessInterface {
    private static final String ACCESS_USERNAME = "appUser";
    private static final String ACCESS_PASSWORD = "myPassword123";
    private static final String CONNECTION_URL = String.format("mongodb+srv://%s:%s@cluster0.dvuik.mongodb.net/",
            ACCESS_USERNAME, ACCESS_PASSWORD)
            + "?retryWrites=true&w=majority&appName=Cluster0";

    // database constants
    private static final String RECIPE_DATABASE_NAME = "recipe_app_db";
    private static final String USERS_COLLECTION_NAME = "users";
    private static final String RECIPES_COLLECTION_NAME = "recipes";

    // User collection constants
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String BOOKMARKED_RECIPE_IDS = "bookmarkedRecipeIds";
    private static final String CREATED_RECIPES = "createdRecipes";
    private static final String INGREDIENTS_TO_AVOID = "ingredientsToAvoid";

    // Recipe collection constants
    private static final String RECIPE_NAME = "recipeName";
    private static final String RECIPE_ID = "recipeId";
    private static final String INGREDIENTS = "ingredients";
    private static final String MEASUREMENTS = "measurements";
    private static final String INSTRUCTIONS = "instructions";
    private static final String IS_ALCOHOLIC = "isAlcoholic";
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 20000;

    private final UserFactory userFactory;
    private final RecipeFactory recipeFactory;

    private final Random random = new Random();

    private String currentUser = "";

    private final MongoClient mongoClient;

    public UserDataAccessObject(UserFactory userFactory, RecipeFactory recipeFactory) {
        this.userFactory = userFactory;
        this.recipeFactory = recipeFactory;
        this.mongoClient = MongoClients.create(CONNECTION_URL);
    }

    @Override
    public boolean existsByName(String username) {
        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);

        final Document foundUser = usersCollection.find(Filters.eq(USERNAME, username)).first();

        return foundUser != null;
    }

    @Override
    public User getUser(String username) {
        if (!existsByName(username)) {
            throw new UserNotFound(username);
        }

        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);

        final Document foundUser = usersCollection.find(Filters.eq(USERNAME, username)).first();

        if (foundUser != null) {
            final String foundUsername = foundUser.getString(USERNAME);
            final String foundPassword = foundUser.getString(PASSWORD);

            return userFactory.create(foundUsername, foundPassword);
        }
        else {
            throw new UserNotFound(username);
        }
    }

    @Override
    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    @Override
    public String getCurrentUser() {
        return currentUser;
    }

    @Override
    public List<Integer> getIngredientsToAvoid(String username) {
        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);
        final Document foundUser = usersCollection.find(Filters.eq(USERNAME, username)).first();

        return foundUser.getList(INGREDIENTS_TO_AVOID, Integer.class, List.of());

    }

    @Override
    public List<Integer> getBookmarkedRecipes(String username) {
        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);
        final Document foundUser = usersCollection.find(Filters.eq(USERNAME, username)).first();

        return foundUser.getList(BOOKMARKED_RECIPE_IDS, Integer.class, List.of());
    }

    @Override
    public void signUp(User user) {
        final String username = user.getName();
        final String password = user.getPassword();

        if (existsByName(username)) {
            throw new UserNotFound(username);
        }

        if (!validatePassword(password) || !validateUsername(username)) {
            return;
        }
        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);

        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);

        // Create a document
        final Document newUser = new Document(USERNAME, username)
                    .append(PASSWORD, password)
                    .append(BOOKMARKED_RECIPE_IDS, List.of())
                    .append(CREATED_RECIPES, List.of())
                    .append(INGREDIENTS_TO_AVOID, List.of(1, 2, 3));

        // Insert the document into the collection
        usersCollection.insertOne(newUser);

    }

    // TODO: Implement these methods to ensure that inputs are valid.
    //  such that: empty strings are not accepted. Change the filler codes below
    private boolean validateUsername(String username) {
        return username.length() >= 3;
    }

    private boolean validatePassword(String password) {
        return password.length() >= 6;
    }

    @Override
    public boolean isBookmarked(String username, int recipeId) {
        if (!existsByName(username)) {
            throw new UserNotFound(username);
        }

        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);

        final Document foundUser = usersCollection.find(Filters.and(
                Filters.eq(USERNAME, username), Filters.eq(BOOKMARKED_RECIPE_IDS, recipeId))
        ).first();
        return foundUser != null;
    }

    @Override
    public void bookmarkRecipe(String username, int recipeId) {
        if (!existsByName(username)) {
            throw new UserNotFound(username);
        }

        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);
        final Document filter = new Document(USERNAME, username);

        // Removes the recipe id from the user bookmarks
        if (isBookmarked(username, recipeId)) {
            final Document update = new Document("$pull", new Document(BOOKMARKED_RECIPE_IDS, recipeId));
            usersCollection.updateOne(filter, update);
        }
        // Adds the recipe id into the user bookmarks
        else {
            final Document update = new Document("$push", new Document(BOOKMARKED_RECIPE_IDS, recipeId));
            usersCollection.updateOne(filter, update);
        }

    }

    @Override
    public void createCustomRecipe(String username, String recipeName,
                                         String recipeInstruction, List<String> ingredients,
                                         List<String> measurements, String isAlcoholic) {
        if (!existsByName(username)) {
            throw new UserNotFound(username);
        }
        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> recipesCollection = database.getCollection(RECIPES_COLLECTION_NAME);

        // Creates the recipe in recipe collection.
        final Document customMongoRecipe = new Document(RECIPE_NAME, recipeName)
                .append(RECIPE_ID, generateRandomId())
                .append(INGREDIENTS, ingredients)
                .append(MEASUREMENTS, measurements)
                .append(INSTRUCTIONS, recipeInstruction)
                .append(IS_ALCOHOLIC, isAlcoholic);
        recipesCollection.insertOne(customMongoRecipe);

        // adds a reference to the created recipe.
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);
        final Document filter = new Document(USERNAME, username);
        final Document update = new Document(
                "$push", new Document(CREATED_RECIPES, customMongoRecipe.getObjectId("_id")));
        usersCollection.updateOne(filter, update);

    }

    @Override
    public void removeCustomRecipe(String username, int id) {
        if (!existsByName(username)) {
            throw new UserNotFound(username);
        }
        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);
        final MongoCollection<Document> recipesCollection = database.getCollection(RECIPES_COLLECTION_NAME);
        // Removes the reference from the user.
        final Document customRecipe = recipesCollection.find(Filters.eq(RECIPE_ID, id)).first();
        if (customRecipe == null) {
            throw new RecipeNotFound(String.format("Recipe ID: %d", id));
        }

        final Document userFilter = new Document(
                USERNAME, username);
        final Document userUpdate = new Document("$pull", new Document(
                CREATED_RECIPES, customRecipe.getObjectId("_id")));
        usersCollection.updateOne(userFilter, userUpdate);
        // Removes the recipe from the recipe collection
        recipesCollection.deleteOne(Filters.eq(RECIPE_ID, id));
    }

    @Override
    public List<Recipe> getCustomRecipes(String username) {
        if (!existsByName(username)) {
            throw new UserNotFound(username);
        }

        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);
        final MongoCollection<Document> recipesCollection = database.getCollection(RECIPES_COLLECTION_NAME);

        final Document foundUser = usersCollection.find(Filters.eq(USERNAME, username)).first();

        final List<ObjectId> createdRecipeIds = foundUser.getList(CREATED_RECIPES, ObjectId.class, List.of());
        final List<Recipe> results = new ArrayList<>();

        for (ObjectId recipeObjectId : createdRecipeIds) {
            final Document recipeObject = recipesCollection.find(Filters.eq("_id", recipeObjectId)).first();
            final int recipeId = recipeObject.getInteger(RECIPE_ID);
            results.add(getRecipeById(recipeId));
        }

        return results;
    }

    private List<Ingredient> getIngredientsEntity(Document recipeObject) {
        final List<Ingredient> result = new ArrayList<>();
        final List<String> ingredients = recipeObject.getList(INGREDIENTS, String.class, List.of());
        final List<String> measurements = recipeObject.getList(MEASUREMENTS, String.class, List.of());
        for (int i = 0; i < ingredients.size(); i++) {
            final String ingredient = ingredients.get(i);
            final String measurement = measurements.get(i);
            result.add(new Ingredient(ingredient, measurement));
        }

        return result;
    }

    private int generateRandomId() {
        // Generate random int between min (inclusive) and max (inclusive)
        int randomInt = random.nextInt((MAX_BOUND - MIN_BOUND) + 1) + MIN_BOUND;

        if (idExists(randomInt)) {
            randomInt = generateRandomId();
        }
        return randomInt;
    }

    private boolean idExists(int id) {
        return idExistsInMongo(id) || idExistsInApi(id);
    }

    private boolean idExistsInMongo(int id) {
        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> recipesCollection = database.getCollection(RECIPES_COLLECTION_NAME);
        final Document foundRecipe = recipesCollection.find(Filters.eq(RECIPE_ID, id)).first();

        return foundRecipe != null;
    }

    private boolean idExistsInApi(int id) {
        return false;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
        final MongoCollection<Document> recipesCollection = database.getCollection(RECIPES_COLLECTION_NAME);
        final Document recipeObject = recipesCollection.find(Filters.eq(RECIPE_ID, recipeId)).first();
        final String recipeName = recipeObject.getString(RECIPE_NAME);
        final String recipeInstruction = recipeObject.getString(INSTRUCTIONS);
        final List<Ingredient> recipeIngredients = getIngredientsEntity(recipeObject);
        final String recipeIsAlcoholic = recipeObject.getString(IS_ALCOHOLIC);

        return recipeFactory.create(recipeName, recipeId,
                recipeInstruction, recipeIngredients,
                "", "", recipeIsAlcoholic);

    }
}
