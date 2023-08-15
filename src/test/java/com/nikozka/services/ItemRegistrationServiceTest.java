package com.nikozka.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nikozka.dao.ItemRepository;
import com.nikozka.dtos.Item;
import org.junit.jupiter.api.Test;

class ItemRegistrationServiceTest {
  ItemRepository itemRepository = mock(ItemRepository.class);
  ItemRegistrationService testObject = new ItemRegistrationService(itemRepository);

  @Test
  public void testSuccessItemRegister() {
    com.nikozka.dtos.Item item = createItemDto();
    when(itemRepository.save(any())).thenReturn(new com.nikozka.dao.entities.Item());
    assertTrue(testObject.registererItem(item));
    verify(itemRepository, times(1)).save(any());
  }

  @Test
  public void testFailItemRegister() {
    var item = createItemDto();
    when(itemRepository.save(any())).thenReturn(null);
    assertFalse(testObject.registererItem(item));
  }

  private Item createItemDto() {
    return new Item("title", "description", "City");
  }
}
