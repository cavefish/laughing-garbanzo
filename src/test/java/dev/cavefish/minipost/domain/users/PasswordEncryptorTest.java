package dev.cavefish.minipost.domain.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptorTest {

    @Test
    void encryptPassword() {
        // Arrange
        String user = "test";
        String password = "password";
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        // Act
        String result = passwordEncryptor.encryptPassword(user, password);
        // Assert
        assertNotNull(result);
        assertNotEquals(password, result);
        assertFalse(password.contains(result));
        assertFalse(result.contains(password));
    }
}