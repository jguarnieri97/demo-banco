package com.demo.banco.controller;

import com.demo.banco.controller.contracts.request.UserRequest;
import com.demo.banco.controller.contracts.response.UserResponse;
import com.demo.banco.exceptions.RegisterUserException;
import com.demo.banco.model.User;
import com.demo.banco.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final ObjectMapper mapper;

    public UserController(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody UserRequest request) {
        User user = userService.registerUser(request);
        UserResponse response = mapper.convertValue(user, UserResponse.class);
        response.setPassword(user.getAuthInfo().getPassword());
        response.setToken(user.getAuthInfo().getToken());
        return response;

    }

}
