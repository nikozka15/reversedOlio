package com.nikozka.dtos.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nikozka.dtos.Item;
import com.nikozka.exceptions.ValidationException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ItemValidatorTest {
  private static final String DEFAULT_TITLE = "Title";
  private static final String DEFAULT_DESCRIPTION = "Description longer than 20 characters";
  private static final String DEFAULT_CITY = "City";

  @ParameterizedTest
  @MethodSource("data")
  void validate(Item item, String expected) {
    ValidationException validationException =
        assertThrows(ValidationException.class, () -> ItemValidator.validate(item));
    assertEquals(expected, validationException.getMessage());
  }

  static List<Object[]> data() {
    return Arrays.asList(
        new Object[] {createItemWithInvalidTitle(), "Title is too short"},
        new Object[] {
          createItemWithInvalidDescription(), "Description should be at least 20 characters long"
        },
        new Object[] {createItemWithInvalidCity(), "Name of the city should contain only letters"});
  }

  private static Item createItemWithInvalidCity() {
    return new Item(DEFAULT_TITLE, DEFAULT_DESCRIPTION, "111");
  }

  private static Item createItemWithInvalidDescription() {
    return new Item(DEFAULT_TITLE, "De", DEFAULT_CITY);
  }

  private static Item createItemWithInvalidTitle() {
    return new Item("Ti", DEFAULT_DESCRIPTION, DEFAULT_CITY);
  }
}
