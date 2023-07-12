package com.demo.banco.service;

import com.demo.banco.controller.contracts.request.UserRequest;

public interface UserService {

    void registerUser(UserRequest request);

}
