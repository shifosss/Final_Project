package entities;

import entities.user.CommonUser;
import entities.user.User;
import entities.user.factory.CommonUserFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "testUser";
        String password = "password123";

        // Act
        CommonUser user = new CommonUser(name, password);

        // Assert
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals("testUser with pass: password123", user.toString());
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // Arrange
        String name = "";
        String password = "";

        // Act
        CommonUser user = new CommonUser(name, password);

        // Assert
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(" with pass: ", user.toString());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String name = null;
        String password = null;

        // Act
        CommonUser user = new CommonUser(name, password);

        // Assert
        assertNull(user.getName());
        assertNull(user.getPassword());
        assertEquals("null with pass: null", user.toString());
    }

    @Test
    void testConstructorWithSpecialCharacters() {
        // Arrange
        String name = "test@User#123";
        String password = "pass!@#$%^&*";

        // Act
        CommonUser user = new CommonUser(name, password);

        // Assert
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals("test@User#123 with pass: pass!@#$%^&*", user.toString());
    }
}

class CommonUserFactoryTest {
    @Test
    void testCreate() {
        // Arrange
        CommonUserFactory factory = new CommonUserFactory();
        String name = "testUser";
        String password = "password123";

        // Act
        User user = factory.create(name, password);

        // Assert
        assertTrue(user instanceof CommonUser);
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testCreateWithEmptyStrings() {
        // Arrange
        CommonUserFactory factory = new CommonUserFactory();
        String name = "";
        String password = "";

        // Act
        User user = factory.create(name, password);

        // Assert
        assertTrue(user instanceof CommonUser);
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testCreateWithNullValues() {
        // Arrange
        CommonUserFactory factory = new CommonUserFactory();
        String name = null;
        String password = null;

        // Act
        User user = factory.create(name, password);

        // Assert
        assertTrue(user instanceof CommonUser);
        assertNull(user.getName());
        assertNull(user.getPassword());
    }
}