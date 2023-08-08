package com.nikozka.services;

import com.nikozka.dao.UserRepository;
import com.nikozka.dtos.User;
import com.nikozka.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User userDto) {
        if (userRepository.findByEmail(userDto.email()) != null) {
            throw new ValidationException("User with this email already exists");
        }
        userRepository.save(convert(userDto));
    }

    public com.nikozka.dao.entities.User convert(User userDto) {

        return new com.nikozka.dao.entities
                .User(UUID.randomUUID(), userDto.email(), userDto.password(), userDto.firstName(), userDto.lastName());
    }
}
