package com.nikozka.endpoints;

import com.nikozka.dtos.Item;
import com.nikozka.dtos.validators.ItemValidator;
import com.nikozka.responses.RegistrationResponse;
import com.nikozka.services.ItemRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRegistrationController {
  private final ItemRegistrationService itemRegistrationService;

  public ItemRegistrationController(ItemRegistrationService itemRegistrationService) {
    this.itemRegistrationService = itemRegistrationService;
  }

  @PostMapping("/registerItem")
  public ResponseEntity<RegistrationResponse> registerItem(@RequestBody @Validated Item item) {
    ItemValidator.validate(item);
    Boolean registererItem = itemRegistrationService.registererItem(item);
    return ResponseEntity.accepted().body(new RegistrationResponse(registererItem));
  }
}
