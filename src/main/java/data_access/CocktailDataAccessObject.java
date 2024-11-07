package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.recipe.Ingredient;
import entities.recipe.factory.RecipeFactory;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entities.recipe.Recipe;
import use_case.recipe_detail.RecipeDetailDataAccessInterface;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * The Data Access Object for recipe api.
 */
public class CocktailDataAccessObject implements
        SearchRecipeDataAccessInterface,
        RecipeDetailDataAccessInterface {
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
        Recipe result = null;
        // http://thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
        final JSONObject responseBody = makeApiRequest(String.format("%s/lookup.php?i=%d", API_URL, id));
        final JSONArray cocktails = getCocktails(responseBody);
        // we will assume that there each id are distinct
        if (cocktails.length() == 1) {
            final JSONObject raw = cocktails.getJSONObject(0);
            result = createRecipe(raw);
        }
        return result;
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
    private Recipe createRecipe(JSONObject raw) {
        final String name = getRecipeName(raw);
        final int id = getRecipeId(raw);
        final String instruction = getInstruction(raw);
        final List<Ingredient> ingredients = getIngredients(raw);
        final String imageLink = getImageLink(raw);

        return cocktailFactory.create(name, id, instruction, ingredients, imageLink);
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
