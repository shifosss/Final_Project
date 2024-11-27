package use_case;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import use_case.change_preference.ChangePreferenceInputData;

import static org.junit.jupiter.api.Assertions.*;

class ChangePreferenceInputDataTest {

    @Test
    void testConstructorAndGetter() {
        // Arrange
        List<String> ingredientsToAvoid = Arrays.asList("Peanuts", "Dairy", "Shellfish");

        // Act
        ChangePreferenceInputData inputData = new ChangePreferenceInputData(ingredientsToAvoid);

        // Assert
        assertEquals(ingredientsToAvoid, inputData.getIngredientsToAvoid());
    }

    @Test
    void testConstructorWithEmptyList() {
        // Arrange
        List<String> emptyList = Collections.emptyList();

        // Act
        ChangePreferenceInputData inputData = new ChangePreferenceInputData(emptyList);

        // Assert
        assertTrue(inputData.getIngredientsToAvoid().isEmpty());
    }

    @Test
    void testConstructorWithNullList() {
        // Arrange
        List<String> nullList = null;

        // Act
        ChangePreferenceInputData inputData = new ChangePreferenceInputData(nullList);

        // Assert
        assertNull(inputData.getIngredientsToAvoid());
    }

    @Test
    void testImmutableListBehavior() {
        // Arrange
        List<String> mutableList = Arrays.asList("Peanuts", "Dairy");
        ChangePreferenceInputData inputData = new ChangePreferenceInputData(mutableList);

        // Act
        List<String> retrievedList = inputData.getIngredientsToAvoid();

        // Try modifying the retrieved list
        assertThrows(UnsupportedOperationException.class, () -> retrievedList.add("Gluten"));

        // Assert
        assertEquals(mutableList, retrievedList);
    }
}
