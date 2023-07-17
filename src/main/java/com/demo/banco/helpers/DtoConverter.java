package com.demo.banco.helpers;

import com.demo.banco.contracts.response.UserResponse;
import com.demo.banco.model.User;

public interface DtoConverter {

    UserResponse convertUserResponse(User user);
}
