package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.recipe.CocktailRecipe;
import entities.recipe.Ingredient;
import entities.recipe.factory.CocktailFactory;
import entities.recipe.factory.RecipeFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entities.recipe.Recipe;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import use_case.explore_ingredient.ExploreIngredientDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * The Data Access Object for recipe api.
 */
public class CocktailDataAccessObject implements SearchRecipeDataAccessInterface, ExploreIngredientDataAccessInterface {
    private static final String API_URL = "http://thecocktaildb.com/api/json/v1/1/";
    private static final int START = 1;
    private static final int END = 15;
    private final RecipeFactory cocktailFactory;

    public CocktailDataAccessObject() {
        this.cocktailFactory = new CocktailFactory();
    }

    @Override
    public List<Recipe> searchRecipeByKeyword(String keyword) {
        // http://thecocktaildb.com/api/json/v1/1/search.php?s=margarita
        final List<Recipe> recipes = new ArrayList<>();
        final JSONObject responseBody = makeApiRequest(String.format("%s/search.php?s=%s", API_URL, keyword));
        return getRecipes(recipes, responseBody);
    }

    @Override
    public Recipe searchRandomRecipe() {
        final JSONObject responseBody = makeApiRequest(String.format("%s/random.php", API_URL));
        final JSONArray cocktails = getCocktails(responseBody);
        final JSONObject raw = cocktails.getJSONObject(0);
        final String name = getRecipeName(raw);
        final int id = getRecipeId(raw);
        final String instruction = getInstruction(raw);
        final List<Ingredient> ingredients = getIngredients(raw);
        final String imageLink = getImageLink(raw);
        return cocktailFactory.create(name, id, instruction, ingredients, imageLink);
    }

    /**
     * @param ingredient
     * @return recipes
     */
    @Override
    public List<Recipe> exploreRecipeByIngredients(String ingredient) {
        final List<Recipe> recipes = new ArrayList<>();
        final JSONObject responseBody = makeApiRequest(String.format("%s/filter.php?i=%s", API_URL, ingredient));
        return getRecipes(recipes, responseBody);
    }

    @Override
    public List<Ingredient> getIngredientsList() {
        final List<Ingredient> ingredients = new ArrayList<>();
        final JSONObject responseBody = makeApiRequest(String.format("%s/list.php?i=list", API_URL));
        final JSONArray ingredientsList = getListOfIngredients(responseBody);

        for (int i = 0; i < ingredientsList.length(); i++) {
            final JSONObject raw = ingredientsList.getJSONObject(i);
            final String name = getIngredientByIdentifier(raw, "strIngredient1");
            ingredients.add(new Ingredient(name, "-1"));
        }

        return ingredients;
    }

    private JSONArray getListOfIngredients(JSONObject responseBody) {
        return responseBody.optJSONArray("drinks", new JSONArray());
    }

    private List<Recipe> getRecipes(List<Recipe> recipes, JSONObject responseBody) {
        final JSONArray cocktails = getCocktails(responseBody);

        for (int i = 0; i < cocktails.length(); i++) {
            final JSONObject raw = cocktails.getJSONObject(i);
            final String name = getRecipeName(raw);
            final int id = getRecipeId(raw);
            final String instruction = getInstruction(raw);
            final List<Ingredient> ingredients = getIngredients(raw);
            final String imageLink = getImageLink(raw);

            recipes.add(cocktailFactory.create(name, id, instruction, ingredients, imageLink));
        }

        return recipes;
    }

    // getCocktails and getIngredientByIdentifier might return null.
    // So, we give them a default value
    private String getIngredientByIdentifier(JSONObject raw, String identifier) {
        return raw.optString(identifier, "");
    }

    private JSONArray getCocktails(JSONObject responseBody) {
        return responseBody.optJSONArray("drinks", new JSONArray());
    }

    // below, we are assuming that each raw recipe jsonobject has the attributes.
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

    private JSONObject makeApiRequest(String apiUrl) throws JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format(apiUrl))
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                return responseBody;
            }
            else {
                throw new IOException("Unexpected code " + response);
            }

        }
        catch (IOException | JSONException exception) {
            throw new RuntimeException(exception);
        }
    }
}
