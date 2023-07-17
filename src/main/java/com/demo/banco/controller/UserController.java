package com.demo.banco.controller;

import com.demo.banco.contracts.request.UserRequest;
import com.demo.banco.contracts.response.UserResponse;
import com.demo.banco.exceptions.InvalidUserRequestException;
import com.demo.banco.helpers.DtoConverter;
import com.demo.banco.model.User;
import com.demo.banco.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final DtoConverter dtoConverter;

    public UserController(UserService userService, DtoConverter dtoConverter) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody UserRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) throw new InvalidUserRequestException();
        User user = userService.registerUser(request);
        return dtoConverter.convertUserResponse(user);
    }

    @GetMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse loginUser(@RequestParam String token) {
        User user = userService.loginUser(token);
        return dtoConverter.convertUserResponse(user);
    }

}
