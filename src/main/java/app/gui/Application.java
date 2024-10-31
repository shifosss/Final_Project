package app.gui;

import app.Config;
import use_case.GetAllCategoriesUseCase;

/**
 * Application that runs the program.
 */

@Deprecated
public class Application {

    /**
     * Main application.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final Config config = new Config();

        final GetAllCategoriesUseCase getAllCategoriesUseCase = config.getAllCategoriesUseCase();

        for (String category: getAllCategoriesUseCase.getCategories()) {
            System.out.println(category);
        }
    }
}
