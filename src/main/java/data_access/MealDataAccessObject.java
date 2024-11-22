package data_access;

import entities.recipe.factory.MealFactory;
import entities.recipe.factory.RecipeFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MealDataAccessObject {

    private final RecipeFactory mealfactory;

    public MealDataAccessObject(RecipeFactory mealFactory) {
        this.mealfactory = mealFactory;
    }

    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?f=a";
    private static final int START = 1;
    private static final int END = 15;
    private final ArrayList<String> mealslist = new ArrayList<>();

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
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public JSONObject getMeals() throws JSONException {
        return makeApiRequest(API_URL);
    }

    public static void main(String[] args) throws JSONException {
        final MealDataAccessObject mealDataAccessObject = new MealDataAccessObject();
        final JSONObject meals = mealDataAccessObject.getMeals();
        System.out.println(meals);
    }

    public int get_length_meals(int index) throws JSONException {
        final JSONObject meals = makeApiRequest(API_URL);
        assert meals != null;
        return meals.getJSONArray("meals").length();
    }
    public ArrayList<String> getIngredients(int index) throws JSONException {
        ArrayList<String> mealsarray = new ArrayList<String>();
        final JSONObject meals = makeApiRequest(API_URL);
        assert meals != null;
        final JSONArray mealsArray = meals.getJSONArray("meals");
        int count = 1;
        int id = 1;
        while (count != 0) {
            if (!Objects.equals(mealsArray.getJSONObject(index).getString("strIngredient" + String.valueOf(id)), "")) {
                mealsarray.add(mealsArray.getJSONObject(index).getString("strIngredient"));
                id++;
            } else {
                count = 0;
            }
        }
        return mealsarray;
    }
    public String get_meal_id(int index) throws JSONException {
        final JSONObject meals = makeApiRequest(API_URL);
        assert meals != null;
        final JSONArray mealsArray = meals.getJSONArray("meals");
        return mealsArray.getJSONObject(index).getString("idMeal");
    }

    public String getRecipe(int index) throws JSONException {
        final JSONObject recipe = makeApiRequest(API_URL);
        assert recipe != null;
        final JSONArray recipesArray = recipe.getJSONArray("meals");
        final String meal = recipesArray.getJSONObject(index).getString("strInstructions");
        return meal;
    }

    public String getMealName(int index) throws JSONException {
        final JSONObject meal = makeApiRequest(API_URL);
        assert meal != null;
        final JSONArray mealsArray = meal.getJSONArray("meals");
        final String mealName = mealsArray.getJSONObject(index).getString("strMeal");
        mealslist.add(mealName);
        return mealName;
    }

    public String getImageUrl(int index) throws JSONException {
        final JSONObject image = makeApiRequest(API_URL);
        assert image != null;
        return image.getJSONArray("images").getJSONObject(index).getString("strImageThumb");
    }

    public String getYoutubeUrl(int index) throws JSONException {
        final JSONObject youtube = makeApiRequest(API_URL);
        assert youtube != null;
        return youtube.getJSONArray("meals").getJSONObject(index).getString("");
    }

    public ArrayList<String> getMeasurement(int index) throws JSONException {
        final JSONObject meal = makeApiRequest(API_URL);
        assert meal != null;
        final ArrayList<String> mealsarray = new ArrayList<>();
        final JSONArray mealsArray = meal.getJSONArray("meals");
        int count = 1;
        int id = 1;
        while (count != 0) {
            if (!Objects.equals(mealsArray.getJSONObject(index).getString("strMeasure" + String.valueOf(id)), "")) {
                mealsarray.add(mealsArray.getJSONObject(index).getString("strMeasure"));
                mealsarray.add(mealsArray.getJSONObject(index).getString("strMeasure"));
            } else {
                count = 0;
            }
        }
        return mealsarray;
    }
}