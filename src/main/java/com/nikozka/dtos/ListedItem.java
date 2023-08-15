package com.nikozka.dtos;

import java.util.UUID;

public record ListedItem(UUID id, Item item) {
    public static ListedItem convert(com.nikozka.dao.entities.Item item) {
        return new ListedItem(item.getId(), new Item(item.getTitle(), item.getDescription(), item.getCity()));
    }
}
