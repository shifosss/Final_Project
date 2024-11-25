package interface_adapter.custom_recipe;


import java.util.ArrayList;
import java.util.List;

public class CustomIngredientsViewModel {
    private final List<String[]> ingredients;

    public CustomIngredientsViewModel() {
        this.ingredients = new ArrayList<>();
        this.ingredients.add(new String[]{"", ""}); // Initialize the first row
    }

    public List<String[]> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void addIngredient() {
        ingredients.add(new String[]{"", ""});
    }

    public void removeIngredient(int index) {
        if (index >= 0 && index < ingredients.size()) {
            ingredients.remove(index);
        }
    }
}
