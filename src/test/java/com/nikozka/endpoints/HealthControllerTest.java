package com.nikozka.endpoints;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(HealthController.class)
class HealthControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void testGetHealth() throws Exception {
    // Perform GET request to "/health" endpoint
    ResultActions resultActions = mockMvc.perform(get("/health"));

    // Verify the response
    resultActions
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("OK. Date: " + LocalDate.now()));
  }
}
