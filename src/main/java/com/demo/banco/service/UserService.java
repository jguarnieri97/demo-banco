package com.demo.banco.service;

import com.demo.banco.contracts.request.UserRequest;
import com.demo.banco.model.User;

public interface UserService {

    User registerUser(UserRequest request);
    User loginUser(String token);

}
