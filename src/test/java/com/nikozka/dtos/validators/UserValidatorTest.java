package com.nikozka.dtos.validators;

import com.nikozka.dtos.User;
import com.nikozka.exceptions.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserValidatorTest {

    private static final String DEFAULT_EMAIL = "abc@gmail.com";
    private static final String DEFAULT_PASSWORD = "123abcABC@";
    private static final String DEFAULT_FIRST_NAME = "Name";
    private static final String DEFAULT_LAST_NAME = "Name";
    private static final Boolean DEFAULT_IS_TC_SIGNED = true;
    private static final String DEFAULT_CONFIRM_PASSWORD = DEFAULT_PASSWORD;

    @ParameterizedTest
    @MethodSource("data")
    void validate(User user, String expected) {

        ValidationException validationException =
                assertThrows(ValidationException.class, () -> UserValidator.validate(user));
        assertEquals(expected, validationException.getMessage());
    }

    static List<Object[]> data() {
        return Arrays.asList(
                new Object[]{createUserWithInvalidEmail(), "Invalid email"},
                new Object[]{createUserWithInvalidPassword(), "Invalid password"},
                new Object[]{createUserWithInvalidFirstName(), "Invalid first name"},
                new Object[]{createUserWithInvalidLastName(), "Invalid last name"},
                new Object[]{createUserWithInvalidTC(), "Invalid terms and conditions"},
                new Object[]{createUserWithNotMatchedPasswords(), "Passwords do not match"}
        );
    }


    private static User createUserWithInvalidEmail() {
        return new User("gmailgmail.com", DEFAULT_PASSWORD, DEFAULT_CONFIRM_PASSWORD,
                DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_IS_TC_SIGNED);
    }

    private static User createUserWithInvalidPassword() {
        return new User(DEFAULT_EMAIL, "123", "123",
                DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_IS_TC_SIGNED);
    }

    private static User createUserWithInvalidFirstName() {
        return new User(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_CONFIRM_PASSWORD,
                "N", DEFAULT_LAST_NAME, DEFAULT_IS_TC_SIGNED);
    }

    private static User createUserWithInvalidLastName() {
        return new User(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_CONFIRM_PASSWORD,
                DEFAULT_FIRST_NAME, "N", DEFAULT_IS_TC_SIGNED);
    }

    private static User createUserWithInvalidTC() {
        return new User(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_CONFIRM_PASSWORD,
                DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, false);
    }

    private static User createUserWithNotMatchedPasswords() {
        return new User(DEFAULT_EMAIL, DEFAULT_PASSWORD, "123",
                DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_IS_TC_SIGNED);
    }
}
