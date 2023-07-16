package com.demo.banco.controller;

import com.demo.banco.controller.contracts.request.PhoneRequest;
import com.demo.banco.controller.contracts.request.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenUserRequest_shouldCreateNewUser() throws Exception {
        PhoneRequest phoneRequest = new PhoneRequest(115566, 11, "AR");
        List<PhoneRequest> list = new ArrayList<>();
        list.add(phoneRequest);
        UserRequest request = new UserRequest("John Doe", "jdoe@example.com", "abcdef1H2", list);
        String jsonBody = objectMapper.writeValueAsString(request);

        MvcResult result = this.mockMvc.perform(
                post("http://localhost/api/v1/users/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals("application/json",
                result.getResponse().getContentType());
    }

}