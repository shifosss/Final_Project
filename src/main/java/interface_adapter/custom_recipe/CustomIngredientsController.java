package interface_adapter.custom_recipe;


public class CustomIngredientsController {
    private final CustomIngredientsViewModel viewModel;

    public CustomIngredientsController(CustomIngredientsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void addIngredient() {
        viewModel.addIngredient(); // Add new empty ingredients
    }

    public void removeIngredient(int index) {
        viewModel.removeIngredient(index); // Remove the ingredient at the specified index
    }
}
