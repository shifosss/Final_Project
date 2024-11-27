package interface_adapter.preference;

import java.util.ArrayList;
import java.util.List;
/**
 * State for the preference view.
 */
public class PreferenceState {
    private String username = "";
    private String excludedIngredients = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExcludedIngredients() {
        return excludedIngredients;
    }

    public void setExcludedIngredients(String excludedIngredients) {
        this.excludedIngredients = excludedIngredients;
    }
}
