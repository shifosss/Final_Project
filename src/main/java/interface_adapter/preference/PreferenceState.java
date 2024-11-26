package interface_adapter.preference;

import java.util.ArrayList;
import java.util.List;

/**
 * State for the preference view.
 */
public class PreferenceState {
    private List<String> ingredientList = new ArrayList<>();
    private String username = "";

    public PreferenceState(PreferenceState copy) {
        this.ingredientList = copy.ingredientList;
        this.username = copy.username;
    }

    public PreferenceState() {
    }

    public List<String> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<String> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
