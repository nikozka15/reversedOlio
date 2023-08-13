package com.nikozka.endpoints;

import com.nikozka.dtos.User;
import com.nikozka.dtos.validators.UserValidator;
import com.nikozka.responses.RegistrationResponse;
import com.nikozka.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

  private final RegistrationService registrationService;

  public RegistrationController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @PostMapping("/register")
  public ResponseEntity<RegistrationResponse> register(@RequestBody @Validated User user) {
    UserValidator.validate(user);
    registrationService.register(user);
    return ResponseEntity.accepted().body(new RegistrationResponse(true));
  }
}
