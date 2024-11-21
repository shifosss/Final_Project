package interface_adapter.home_page;

import java.util.ArrayList;
import java.util.List;

/**
 * State for the home page.
 */
public class HomePageState {
    private String username = "";
    private String query;
    private List<Integer> ingredientsToAvoidId = new ArrayList<>();

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setIngredientsToAvoidId(List<Integer> ingredientsToAvoidId) {
        this.ingredientsToAvoidId = ingredientsToAvoidId;
    }

    public List<Integer> getIngredientsToAvoidId() {
        return ingredientsToAvoidId;
    }
}
