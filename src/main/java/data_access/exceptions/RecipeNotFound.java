package data_access.exceptions;

/**
 * To be thrown once a recipe is not found via api calls.
 */
public class RecipeNotFound extends RuntimeException {
    public RecipeNotFound(String message) {
        super(message);
    }
}
