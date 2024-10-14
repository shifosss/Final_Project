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
 * MealsDatabase.
 */
public class MealsDatabase implements RecipeDatabase {

    @Override
    public String[] getCategories() {
        return new String[0];
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
