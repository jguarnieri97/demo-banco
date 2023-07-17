package com.demo.banco.controller;

import com.demo.banco.contracts.request.UserRequest;
import com.demo.banco.contracts.response.UserResponse;
import com.demo.banco.helpers.UserFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void givenUserRequest_shouldCreateNewUser() throws Exception {
        UserRequest request = UserFactory.createUserRequest("jdoe@example.com", "abcdef1H2");
        String jsonBody = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("http://localhost/api/v1/users/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void givenMailNull_shouldResponseBadRequest() throws Exception {
        UserRequest request = UserFactory.createUserRequest(null, "abcdef1H2");
        String jsonBody = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("http://localhost/api/v1/users/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenBadUserParams_shouldResponseBadRequest() throws Exception {
        UserRequest request = UserFactory.createUserRequest("jdoeexample.com", "abcdef1H2");
        String jsonBody = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(
                        post("http://localhost/api/v1/users/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenLoginToken_ifUserNotExist_shouldResponseNotFound() throws Exception {
        String token = "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ";

        this.mockMvc.perform(
                        get("http://localhost/api/v1/users/login?token=" + token)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void givenUserPersisted_whenUserRegistered_shouldLoginAndChangeAccessToken() throws Exception {
        UserRequest request = UserFactory.createUserRequest("jdoe@example.com", "abcdef1H2");
        String jsonBody = objectMapper.writeValueAsString(request);

        MvcResult resultPost = this.mockMvc.perform(
                        post("http://localhost/api/v1/users/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBody))
                .andReturn();

        String jsonResponse = resultPost.getResponse().getContentAsString();
        UserResponse userResponse = objectMapper.readValue(jsonResponse, UserResponse.class);

        MvcResult resultGet = this.mockMvc.perform(
                        get("http://localhost/api/v1/users/login?token=" + userResponse.getToken())
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        String getResponse = resultGet.getResponse().getContentAsString();
        UserResponse userGetResponse = objectMapper.readValue(getResponse, UserResponse.class);

        Assertions.assertNotEquals(userResponse.getToken(), userGetResponse.getToken());
    }


}