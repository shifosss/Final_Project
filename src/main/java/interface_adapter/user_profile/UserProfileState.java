package interface_adapter.user_profile;

import entities.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * State of the user profile.
 */
public class UserProfileState {
    private String username = "";
    private List<Recipe> createdRecipes = new ArrayList<>();

    public UserProfileState(UserProfileState copy) {
        this.username = copy.username;
        this.createdRecipes = copy.createdRecipes;
    }

    public UserProfileState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(List<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }
}
