package com.nikozka.endpoints;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nikozka.dtos.Item;
import com.nikozka.dtos.ListedItem;
import com.nikozka.services.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

  @MockBean private ItemService itemService;

  @Autowired private MockMvc mockMvc;

  @Test
  public void testPostRegisterItemWithoutBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/registerItem"));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void testPostRegisterItemWithBody() throws Exception {
    when(itemService.registererItem(any())).thenReturn(true);
        mockMvc.perform(
            post("/registerItem").contentType(MediaType.APPLICATION_JSON).content(createItem()))
        .andExpect(status().isAccepted())
        .andExpect(content().string("{\"registered\":true}"));
  }
  @Test
  public void testGetListedItemsWhenPresent() throws Exception {
    List<ListedItem> listedItems = List.of(new ListedItem(UUID.fromString("ae0642cb-7b95-4d3d-b56e-bf3f0f0df76c"), createItemObject()));
    when(itemService.getListedItems()).thenReturn(listedItems);
    mockMvc.perform(get("/listedItems"))
            .andExpect(status().isOk())
            .andExpect(content().json(createListedItems()));
  }

  private String createListedItems() {
    return "[{\"id\":\"ae0642cb-7b95-4d3d-b56e-bf3f0f0df76c\",\"item\":{\"title\":\"Title\",\"description\":\"Description should contain 20 symbols\",\"city\":\"City\"}}]";
  }

  @Test
  public void testGetListedItemsWhenNotPresent() throws Exception{
    when(itemService.getListedItems()).thenReturn(List.of());
       mockMvc.perform(get("/listedItems"))
                .andExpect(status().isNoContent());
  }
  @Test
  public void testGetItemByIdWhenPresent() throws Exception {
    when(itemService.findItemById(any())).thenReturn(createItemObject());
    mockMvc.perform(get("/item/{itemId}/find", "ae0642cb-7b95-4d3d-b56e-bf3f0f0df76c"))
            .andExpect(status().isOk())
            .andExpect(content().json(createItem()));
  }

  @Test
    public void testGetItemByIdWhenNotPresent() throws Exception {
        when(itemService.findItemById(any())).thenReturn(null);
        mockMvc.perform(get("/item/{itemId}/find", "ae0642cb-7b95-4d3d-b56e-bf1faf0df76c"))
                .andExpect(status().isNotFound());
    }

  private Item createItemObject() {
    return new Item("Title", "Description should contain 20 symbols", "City");
  }

  private String createItem() {
    return "{ \"title\": \"Title\", "
        + "\"description\": \"Description should contain 20 symbols\", "
        + "\"city\": \"City\"}";
  }
}
