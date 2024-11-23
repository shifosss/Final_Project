package use_case.bookmark_meal_recipe;

import use_case.bookmark_recipe.BookmarkRecipeInputData;

public interface BookmarkMealRecipeInputBoundary {

    /**
     * Bookmarks the recipe given the inputs.
     * @param bookmarkmealRecipeInputData the input data.
     */
    void bookmarkRecipe(BookmarkMealRecipeInputData bookmarkmealRecipeInputData);
}
