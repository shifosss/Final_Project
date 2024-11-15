package interface_adapter.home_page;

import java.util.ArrayList;
import java.util.List;

/**
 * State for the home page.
 */
public class HomePageState {
    private String username = "";
    private List<String> ingredientsToAvoidId = new ArrayList<>();

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setIngredientsToAvoidId(List<String> ingredientsToAvoidId) {
        this.ingredientsToAvoidId = ingredientsToAvoidId;
    }

    public List<String> getIngredientsToAvoidId() {
        return ingredientsToAvoidId;
    }
}
