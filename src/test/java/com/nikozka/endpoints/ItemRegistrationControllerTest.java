package com.nikozka.endpoints;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nikozka.services.ItemRegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ItemRegistrationController.class)
class ItemRegistrationControllerTest {

  @MockBean private ItemRegistrationService itemRegistrationService;

  @Autowired private MockMvc mockMvc;

  @Test
  public void testPostRegisterItemWithoutBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/registerItem"));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void testPostRegisterItemWithBody() throws Exception {
    when(itemRegistrationService.registererItem(any())).thenReturn(true);
    ResultActions resultActions =
        mockMvc.perform(
            post("/registerItem").contentType(MediaType.APPLICATION_JSON).content(createItem()));
    resultActions
        .andExpect(status().isAccepted())
        .andExpect(content().string("{\"registered\":true}"));
  }

  private String createItem() {
    return "{ \"title\": \"Title\", "
        + "\"description\": \"Description should contain 20 symbols\", "
        + "\"city\": \"City\"}";
  }
}
