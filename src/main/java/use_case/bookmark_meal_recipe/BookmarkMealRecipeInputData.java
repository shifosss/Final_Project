package use_case.bookmark_meal_recipe;

public class BookmarkMealRecipeInputData {
    private final String username;
    private final String meal_recipe_Id;

    public BookmarkMealRecipeInputData(String username, String recipeId) {
        this.username = username;
        this.meal_recipe_Id = recipeId;
    }

    public String getUsername() {
        return username;
    }

    public String getRecipeId() {
        return meal_recipe_Id;
    }
}
