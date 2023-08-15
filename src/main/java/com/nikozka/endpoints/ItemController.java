package com.nikozka.endpoints;

import com.nikozka.dtos.Item;
import com.nikozka.dtos.ListedItem;
import com.nikozka.dtos.validators.ItemValidator;
import com.nikozka.responses.RegistrationResponse;
import com.nikozka.services.ItemService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping("/registerItem")
  public ResponseEntity<RegistrationResponse> registerItem(@RequestBody @Validated Item item) {
    ItemValidator.validate(item);
    Boolean registererItem = itemService.registererItem(item);
    return ResponseEntity.accepted().body(new RegistrationResponse(registererItem));
  }

  @GetMapping("/listedItems")
  public ResponseEntity<List<ListedItem>> getListedItems() {
    List<ListedItem> listedItems = itemService.getListedItems();
    return listedItems.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(listedItems);
  }

  @GetMapping("/item/{itemId}/find")
  public ResponseEntity<Item> findItem(@PathVariable UUID itemId) {
    Item foundItem = itemService.findItemById(itemId);
    return foundItem != null ? ResponseEntity.ok(foundItem) : ResponseEntity.notFound().build();
  }
}
