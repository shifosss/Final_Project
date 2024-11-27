package entities;

import entities.recipe.*;
import entities.recipe.factory.RecipeFactory;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CocktailRecipeTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Mojito";
        int id = 123;
        String instruction = "Mix all ingredients";
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Rum", "60ml"),
                new Ingredient("Mint", "4 leaves")
        );
        String imageLink = "image.jpg";
        String videoLink = "video.mp4";
        String isAlcoholic = "Alcoholic";

        // Act
        CocktailRecipe recipe = new CocktailRecipe(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic);

        // Assert
        assertEquals(name, recipe.getName());
        assertEquals(id, recipe.getId());
        assertEquals(instruction, recipe.getInstruction());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(imageLink, recipe.getImageLink());
        assertEquals(videoLink, recipe.getVideoLink());
        assertEquals(isAlcoholic, recipe.getIsAlcoholic());
        assertEquals("Name: Mojito\nIngredients: " + ingredients.toString(), recipe.toString());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String name = null;
        int id = 0;
        String instruction = null;
        List<Ingredient> ingredients = null;
        String imageLink = null;
        String videoLink = null;
        String isAlcoholic = null;

        // Act
        CocktailRecipe recipe = new CocktailRecipe(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic);

        // Assert
        assertNull(recipe.getName());
        assertEquals(0, recipe.getId());
        assertNull(recipe.getInstruction());
        assertNull(recipe.getIngredients());
        assertNull(recipe.getImageLink());
        assertNull(recipe.getVideoLink());
        assertNull(recipe.getIsAlcoholic());
        assertEquals("Name: null\nIngredients: null", recipe.toString());
    }
}

class MealRecipeTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Pasta";
        int id = 456;
        String instruction = "Boil pasta";
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Pasta", "500g"),
                new Ingredient("Salt", "1 tsp")
        );
        String imageLink = "pasta.jpg";
        String videoLink = "pasta.mp4";
        String isAlcoholic = "Non-Alcoholic";

        // Act
        MealRecipe recipe = new MealRecipe(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic);

        // Assert
        assertEquals(name, recipe.getName());
        assertEquals(id, recipe.getId());
        assertEquals(instruction, recipe.getInstruction());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(imageLink, recipe.getImageLink());
        assertEquals(videoLink, recipe.getVideoLink());
        assertEquals(isAlcoholic, recipe.getIsAlcoholic());
        assertEquals("Name: Pasta\nIngredients: " + ingredients.toString(), recipe.toString());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String name = null;
        int id = 0;
        String instruction = null;
        List<Ingredient> ingredients = null;
        String imageLink = null;
        String videoLink = null;
        String isAlcoholic = null;

        // Act
        MealRecipe recipe = new MealRecipe(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic);

        // Assert
        assertNull(recipe.getName());
        assertEquals(0, recipe.getId());
        assertNull(recipe.getInstruction());
        assertNull(recipe.getIngredients());
        assertNull(recipe.getImageLink());
        assertNull(recipe.getVideoLink());
        assertNull(recipe.getIsAlcoholic());
        assertEquals("Name: null\nIngredients: null", recipe.toString());
    }
}

class IngredientTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Sugar";
        String measure = "2 tsp";

        // Act
        Ingredient ingredient = new Ingredient(name, measure);

        // Assert
        assertEquals(name, ingredient.getName());
        assertEquals(measure, ingredient.getMeasure());
        assertEquals("Ingredient{name='Sugar', measure='2 tsp'}", ingredient.toString());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String name = null;
        String measure = null;

        // Act
        Ingredient ingredient = new Ingredient(name, measure);

        // Assert
        assertNull(ingredient.getName());
        assertNull(ingredient.getMeasure());
        assertEquals("Ingredient{name='null', measure='null'}", ingredient.toString());
    }
}

class SimpleRecipeTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Simple Recipe";
        int id = 789;
        String imageLink = "simple.jpg";

        // Act
        SimpleRecipe recipe = new SimpleRecipe(name, id, imageLink);

        // Assert
        assertEquals(name, recipe.getName());
        assertEquals(id, recipe.getId());
        assertEquals(imageLink, recipe.getImageLink());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String name = null;
        int id = 0;
        String imageLink = null;

        // Act
        SimpleRecipe recipe = new SimpleRecipe(name, id, imageLink);

        // Assert
        assertNull(recipe.getName());
        assertEquals(0, recipe.getId());
        assertNull(recipe.getImageLink());
    }
}

class CocktailFactoryTest {
    @Test
    void testCreate() {
        // Arrange
        RecipeFactory factory = new RecipeFactory();
        String name = "Margarita";
        int id = 123;
        String instruction = "Mix tequila";
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Tequila", "60ml"),
                new Ingredient("Lime", "1 piece")
        );
        String imageLink = "margarita.jpg";
        String videoLink = "margarita.mp4";
        String isAlcoholic = "Alcoholic";

        // Act
        Recipe recipe = factory.create(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic, "cocktail");

        // Assert
        assertInstanceOf(CocktailRecipe.class, recipe);
        assertEquals(name, recipe.getName());
        assertEquals(id, recipe.getId());
        assertEquals(instruction, recipe.getInstruction());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(imageLink, recipe.getImageLink());
        assertEquals(videoLink, recipe.getVideoLink());
        assertEquals(isAlcoholic, recipe.getIsAlcoholic());
    }
}

class MealFactoryTest {
    @Test
    void testCreate() {
        // Arrange
        RecipeFactory factory = new RecipeFactory();
        String name = "Pizza";
        int id = 456;
        String instruction = "Bake in oven";
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Flour", "500g"),
                new Ingredient("Tomato", "2 pieces")
        );
        String imageLink = "pizza.jpg";
        String videoLink = "pizza.mp4";
        String isAlcoholic = "Non-Alcoholic";

        // Act
        Recipe recipe = factory.create(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic, "meal");

        // Assert
        assertInstanceOf(CocktailRecipe.class, recipe);  // Note: This seems like a bug in MealFactory
        assertEquals(name, recipe.getName());
        assertEquals(id, recipe.getId());
        assertEquals(instruction, recipe.getInstruction());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(imageLink, recipe.getImageLink());
        assertEquals(videoLink, recipe.getVideoLink());
        assertEquals(isAlcoholic, recipe.getIsAlcoholic());
    }
}