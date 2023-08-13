package com.nikozka.dtos.validators;

import com.nikozka.dtos.Item;
import com.nikozka.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;

public class ItemValidator {
  public static void validate(Item item) {
    List<String> result = new ArrayList<>();

    if (validateField(item.title())) {
      result.add("Title is too short");
    }
    if (!validateDescription(item.description())) {
      result.add("Description should be at least 20 characters long");
    }
    if (!validateCity(item.city())) {
      result.add("Name of the city should contain only letters");
    }
    if (validateField(item.city())) {
      result.add("City is too short");
    }
    if (!result.isEmpty()) {
      throw new ValidationException(String.join(", ", result));
    }
  }

  private static boolean validateDescription(String description) {
    return description != null && description.length() > 20;
  }

  private static boolean validateField(String description) {
    return description == null || description.length() <= 2;
  }

  private static boolean validateCity(String title) {
    return title != null && title.matches("^[a-zA-Z]{2,}$");
  }
}
