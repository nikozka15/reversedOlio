package com.nikozka.services;

import com.nikozka.dao.ItemRepository;
import com.nikozka.dtos.Item;

import java.util.List;
import java.util.UUID;

import com.nikozka.dtos.ListedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  ItemRepository itemRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository) {
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

  public List<ListedItem> getListedItems() {
    return itemRepository.findAll().stream()
            .filter(com.nikozka.dao.entities.Item::getListed)
            .map(ListedItem::convert).toList();
  }

  public Item findItemById(UUID itemId) {
    return ListedItem.convert(itemRepository.findById(itemId).get()).item();
  }
}
