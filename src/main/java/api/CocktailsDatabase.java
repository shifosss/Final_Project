package api;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Constants;
import entity.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Cocktails Database (Technically should be a drink database as it consists not only cocktails).
 */
public class CocktailsDatabase implements RecipeDatabase {
    @Override
    public String[] getCategories() {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // http://thecocktaildb.com/api/json/v1/1/list.php?c=list
        final Request request = new Request.Builder()
                .url(String.format("%slist.php?c=list", Constants.DRINK_API_URL))
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Add a status code check later.
            final JSONArray drinks = responseBody.getJSONArray(Constants.DRINKS);
            final String[] categories = new String[drinks.length()];

            for (int i = 0; i < drinks.length(); i++) {
                final JSONObject category = drinks.getJSONObject(i);
                categories[i] = category.getString(Constants.CATEGORY);
            }
            return categories;
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    @Override
    public String[] getIngredients() {
        return new String[0];
    }

    @Override
    public Recipe[] searchRecipe(String query) {
        return new Recipe[0];
    }
}
