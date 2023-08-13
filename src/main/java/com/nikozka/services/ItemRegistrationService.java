package com.nikozka.services;

import com.nikozka.dao.ItemRepository;
import com.nikozka.dtos.Item;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemRegistrationService {
  ItemRepository itemRepository;

  @Autowired
  public ItemRegistrationService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public Boolean registererItem(Item itemDto) {
    return itemRepository.save(convert(itemDto)) != null;
  }

  private com.nikozka.dao.entities.Item convert(Item itemDto) {

    return new com.nikozka.dao.entities.Item(
        UUID.randomUUID(),
        itemDto.title(),
        itemDto.description(),
        itemDto.city(),
        true,
        false,
        false);
  }
}
