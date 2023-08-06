package com.nikozka.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostRegisterWithoutBody() throws Exception {
        // Perform GET request to "/health" endpoint
        ResultActions resultActions = mockMvc.perform(post("/register"));

        // Verify the response
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    public void testPostRegisterWithBody() throws Exception {


        ResultActions resultActions = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUser("gmail@gmail.com", true)));

        // Verify the response
        resultActions.andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string(createResponse()));
    }

    @Test
    public void testPostRegisterWithInvalidBody() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUser("gmailgmail.com", false)));

        // Verify the response
        resultActions.andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(createErrorResponse()));
    }

    private String createErrorResponse() {
        return String.format("{\"errorMessage\":\"%s\"}", "Invalid email, Invalid terms and conditions");
    }


    private static String createUser(String email, Boolean termsAndConditions) {
        return String.format("{ \"email\": \"%s\", " +
                "\"password\": \"!2nik0zka1!ASD\", " +
                "\"confirmPassword\": \"!2nik0zka1!ASD\", " +
                "\"firstName\": \"niko\", " +
                "\"lastName\": \"ka\", " +
                "\"isTCSigned\": \"%s\" " +
                "}", email, termsAndConditions);
    }

    private static String createResponse() {
        return String.format("{\"registered\":%s}", true);
    }
}