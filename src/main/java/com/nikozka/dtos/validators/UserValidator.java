package com.nikozka.dtos.validators;

import com.nikozka.dtos.User;
import com.nikozka.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;

public class UserValidator {
  public static void validate(User user) {
    List<String> result = new ArrayList<>();

    if (!validateEmail(user.email())) {
      result.add("Invalid email");
    }
    if (!validatePassword(user.password())) {
      result.add("Invalid password");
    }
    if (!validateFirstName(user.firstName())) {
      result.add("Invalid first name");
    }
    if (!validateLastName(user.lastName())) {
      result.add("Invalid last name");
    }
    if (!validateIsTCSigned(user.isTCSigned())) {
      result.add("Invalid terms and conditions");
    }
    if (!validateConfirmPassword(user.password(), user.confirmPassword())) {
      result.add("Passwords do not match");
    }
    if (!result.isEmpty()) {
      throw new ValidationException(String.join(", ", result));
    }
  }

  private static boolean validateEmail(String email) {
    return email != null && email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
  }

  private static boolean validatePassword(String password) {
    return password != null
        && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
  }

  private static boolean validateFirstName(String firstName) {
    return firstName != null && firstName.matches("^[a-zA-Z]{2,}$");
  }

  private static boolean validateLastName(String lastName) {
    return lastName != null && lastName.matches("^[A-Za-z'-]{2,}+$");
  }

  private static boolean validateIsTCSigned(Boolean isTCSigned) {
    return isTCSigned != null && isTCSigned;
  }

  private static boolean validateConfirmPassword(String password, String confirmPassword) {
    return password != null && password.equals(confirmPassword);
  }
}
