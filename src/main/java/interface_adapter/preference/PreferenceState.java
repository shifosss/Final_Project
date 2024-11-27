package interface_adapter.preference;

import java.util.ArrayList;
import java.util.List;

/**
 * State for the preference view.
 */
public class PreferenceState {
    private String username = "";
    private List<String> ingredients = new ArrayList<>();

    public PreferenceState(PreferenceState copy) {
        this.ingredients = copy.ingredients;
        this.username = copy.username;
    }

    public PreferenceState() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
