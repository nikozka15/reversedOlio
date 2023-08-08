package com.nikozka.services;

import com.nikozka.dao.UserRepository;
import com.nikozka.dao.entities.User;
import com.nikozka.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegistrationServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    RegistrationService testObject = new RegistrationService(userRepository);

    @Test
    public void testFailedRegister() {
        com.nikozka.dtos.User user = createDummyUserDto();
            when(userRepository.findByEmail("email")).thenReturn(
                new User(null, null, null, "Okosad", null));

        ValidationException validationException =
                assertThrows(ValidationException.class, () -> testObject.register(user));
        assertEquals("User with this email already exists", validationException.getMessage());
    }

    @Test
    public void testSuccessRegister() {
        com.nikozka.dtos.User user = createDummyUserDto();

        testObject.register(user);
        verify(userRepository, times(1)).save(any());
    }

    private static com.nikozka.dtos.User createDummyUserDto() {
        return new com.nikozka.dtos.User("email", "pass",
                "pass", "fn", "ln", false);
    }
}
