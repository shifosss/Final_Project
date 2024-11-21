package data_access;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import entities.user.User;
import entities.user.factory.CommonUserFactory;
import entities.user.factory.UserFactory;
import exceptions.UserNotFound;
import org.bson.Document;
import use_case.bookmark_recipe.BookmarkRecipeDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

import java.util.List;

/**
 * The Data Access Object for users.
 */
public class UserDataAccessObject implements LoginDataAccessInterface,
        SignupDataAccessInterface, BookmarkRecipeDataAccessInterface {
    private static final String ACCESS_USERNAME = "appUser";
    private static final String ACCESS_PASSWORD = "myPassword123";
    private static final String CONNECTION_URL = String.format("mongodb+srv://%s:%s@cluster0.dvuik.mongodb.net/",
            ACCESS_USERNAME, ACCESS_PASSWORD)
            + "?retryWrites=true&w=majority&appName=Cluster0";

    private static final String USERNAME_ATTRIBUTE = "username";
    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final String BOOKMARKS_ATTRIBUTE = "bookmarkedRecipeIds";
    private static final String CREATED_RECIPES_ATTRIBUTE = "createdRecipes";
    private static final String INGREDIENTS_ATTRIBUTE = "ingredientsToAvoid";

    private static final String RECIPE_DATABASE_NAME = "recipe_app_db";
    private static final String USERS_COLLECTION_NAME = "users";
    private static final String RECIPES_COLLECTION_NAME = "recipes";

    private final UserFactory userFactory;

    private String currentUser = "";

    public UserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public boolean existsByName(String username) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_URL)) {
            final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
            final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);

            final Document foundUser = usersCollection.find(Filters.eq("username", username)).first();

            return foundUser != null;
        }
    }

    @Override
    public User getUser(String username) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_URL)) {
            if (!existsByName(username)) {
                throw new UserNotFound(username);
            }

            final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
            final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);

            final Document foundUser = usersCollection.find(Filters.eq("username", username)).first();

            if (foundUser != null) {
                final String foundUsername = foundUser.getString("username");
                final String foundPassword = foundUser.getString("password");

                return userFactory.create(foundUsername, foundPassword);
            }
            else {
                throw new UserNotFound(username);
            }
        }
        catch (UserNotFound exception) {
            System.out.println("FAILED TO FIND USER: " + exception.getMessage());
        }
        return null;
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
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_URL)) {
            final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
            final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);
            final Document foundUser = usersCollection.find(Filters.eq("username", username)).first();

            return foundUser.getList("ingredientsToAvoid", Integer.class, List.of());
        }
    }

    @Override
    public void signUp(User user) {
        final String username = user.getName();
        final String password = user.getPassword();

        if (!validatePassword(password) || !validateUsername(username)) {
            return;
        }
        if (existsByName(username)) {
            return;
        }
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_URL)) {
            final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);

            final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);

            // Create a document
            final Document newUser = new Document("username", username)
                    .append("password", password)
                    .append("bookmarkedRecipeIds", List.of())
                    .append("createdRecipes", List.of())
                    .append("ingredientsToAvoid", List.of(1, 2, 3));

            // Insert the document into the collection
            usersCollection.insertOne(newUser);
        }
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
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_URL)) {
            final MongoDatabase database = mongoClient.getDatabase(RECIPE_DATABASE_NAME);
            final MongoCollection<Document> usersCollection = database.getCollection(USERS_COLLECTION_NAME);

            final Document foundUser = usersCollection.find(Filters.and(
                    Filters.eq("username", username), Filters.eq("bookmarkedRecipeIds", 11007))
            ).first();
            return foundUser != null;
        }
    }

    @Override
    public void bookmarkRecipe(String username, int recipeId) {
    }

    public static void main(String[] args) {
        final UserFactory userFactory1 = new CommonUserFactory();
        final UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory1);
        System.out.println(userDataAccessObject.isBookmarked("shin", 11007));
    }
}
