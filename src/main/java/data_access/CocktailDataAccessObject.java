package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.recipe.Ingredient;
import entities.recipe.factory.RecipeFactory;
import exceptions.IdentifierOverlap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entities.recipe.Recipe;
import use_case.explore_ingredient.ExploreIngredientDataAccessInterface;
import use_case.random_recipes.RandomRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeDataAccessInterface;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * The Data Access Object for recipe api.
 */
public class CocktailDataAccessObject implements
        SearchRecipeDataAccessInterface,
        ViewRecipeDataAccessInterface,
        RandomRecipeDataAccessInterface,
        ExploreIngredientDataAccessInterface {
    private static final String API_URL = "http://thecocktaildb.com/api/json/v1/1";
    private static final int START = 1;
    private static final int END = 15;
    private final RecipeFactory cocktailFactory;

    public CocktailDataAccessObject(RecipeFactory cocktailFactory) {
        this.cocktailFactory = cocktailFactory;
    }

    @Override
    public List<Recipe> searchRecipeByKeyword(String keyword) {
        // http://thecocktaildb.com/api/json/v1/1/search.php?s=margarita
        final List<Recipe> recipes = new ArrayList<>();
        final JSONObject responseBody = makeApiRequest(String.format("%s/search.php?s=%s", API_URL, keyword));
        final JSONArray cocktails = getCocktails(responseBody);

        for (int i = 0; i < cocktails.length(); i++) {
            final JSONObject raw = cocktails.getJSONObject(i);
            recipes.add(createRecipe(raw));
        }

        return recipes;
    }

    @Override
    public Recipe getRecipeById(int id) {
        // http://thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
        final JSONObject responseBody = makeApiRequest(String.format("%s/lookup.php?i=%d", API_URL, id));
        final JSONArray cocktails = getCocktails(responseBody);
        if (cocktails.length() != 1) {
            throw new IdentifierOverlap("Multiple existing ids");
        }
        final JSONObject raw = cocktails.getJSONObject(0);
        return createRecipe(raw);
    }

    @Override
    public List<Recipe> getRandomRecipes(int limit) {
        final List<Recipe> recipes = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            final JSONObject responseBody = makeApiRequest(String.format("%s/random.php", API_URL));
            final JSONArray cocktails = getCocktails(responseBody);

            final JSONObject raw = cocktails.getJSONObject(0);
            recipes.add(createRecipe(raw));
        }

        return recipes;
    }

    @Override
    public List<Recipe> exploreRecipeByIngredients(String ingredient) {
        // https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin
        final List<Recipe> recipes = new ArrayList<>();
        final JSONObject responseBody = makeApiRequest(String.format("%s/filter.php?i=%s", API_URL, ingredient));
        final JSONArray cocktails = getCocktails(responseBody);

        for (int i = 0; i < cocktails.length() && i < 10; i++) {
            final JSONObject raw = cocktails.getJSONObject(i);
            final int recipeId = getRecipeId(raw);
            System.out.println(recipeId);
            recipes.add(getRecipeById(recipeId));
        }
        return recipes;
    }

    @Override
    public List<String> getIngredientsList() {
        final List<String> ingredientsList = new ArrayList<>();
        // https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list
        final JSONObject responseBody = makeApiRequest(String.format("%s/list.php?i=list", API_URL));
        final JSONArray ingredients = getCocktails(responseBody);

        for (int i = 0; i < ingredients.length(); i++) {
            final JSONObject raw = ingredients.getJSONObject(i);
            final String ingredientName = getIngredientByIdentifier(raw, "strIngredient1");
            ingredientsList.add(ingredientName);
        }

        return ingredientsList;
    }

    // getCocktails and getIngredientByIdentifier might return null.
    // So, we give them a default value
    private String getIngredientByIdentifier(JSONObject raw, String identifier) {
        return raw.optString(identifier, "");
    }

    private JSONArray getCocktails(JSONObject responseBody) {
        return responseBody.optJSONArray("drinks", new JSONArray());
    }

    private String getVideoLink(JSONObject raw) {
        return raw.optString("strVideo", "");
    }

    // below, we are assuming that each raw recipe jsonobject has the attributes.
    private Recipe createRecipe(JSONObject raw) {
        final String name = getRecipeName(raw);
        final int id = getRecipeId(raw);
        final String instruction = getInstruction(raw);
        final List<Ingredient> ingredients = getIngredients(raw);
        final String imageLink = getImageLink(raw);
        final String videoLink = getVideoLink(raw);
        final String isAlcoholic = getIsAlcoholic(raw);

        return cocktailFactory.create(name, id, instruction, ingredients, imageLink, videoLink, isAlcoholic);
    }

    private String getRecipeName(JSONObject raw) {
        return raw.getString("strDrink");
    }

    private int getRecipeId(JSONObject raw) {
        return Integer.parseInt(raw.getString("idDrink"));
    }

    private String getInstruction(JSONObject raw) {
        return raw.getString("strInstructions");
    }

    private String getImageLink(JSONObject raw) {
        return raw.getString("strDrinkThumb");
    }

    private String getIsAlcoholic(JSONObject raw) {
        return raw.getString("strAlcoholic");
    }

    private List<Ingredient> getIngredients(JSONObject raw) {
        final List<Ingredient> ingredients = new ArrayList<>();
        for (int i = START; i <= END; i++) {
            final String ingredientIdentifier = String.format("strIngredient%s", i);
            final String measurementIdentifier = String.format("strMeasure%s", i);

            final String ingredientName = getIngredientByIdentifier(raw, ingredientIdentifier);
            final String ingredientMeasure = getIngredientByIdentifier(raw, measurementIdentifier);

            ingredients.add(new Ingredient(ingredientName, ingredientMeasure));
        }
        return ingredients;
    }

    protected JSONObject makeApiRequest(String apiUrl) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format(apiUrl))
                .build();
        try {
            try (Response response = client.newCall(request).execute()) {
                final JSONObject responseBody = new JSONObject(response.body().string());

                if (response.isSuccessful()) {
                    return responseBody;
                }
                else {
                    throw new IOException("Unexpected code " + response);
                }
            }

        }
        catch (IOException | JSONException exception) {
            throw new RuntimeException(exception);
        }
    }
}
