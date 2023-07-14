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

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody UserRequest request) {
        User user = userService.registerUser(request);
        return convertResponse(user);
    }

    @GetMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse loginUser(@RequestParam String token) {
        User user = userService.loginUser(token);
        return convertResponse(user);
    }

    private UserResponse convertResponse(User user) {
        UserResponse response = mapper.convertValue(user, UserResponse.class);
        response.setPassword(user.getAuthInfo().getPassword());
        response.setToken(user.getAuthInfo().getToken());
        return response;
    }

}
