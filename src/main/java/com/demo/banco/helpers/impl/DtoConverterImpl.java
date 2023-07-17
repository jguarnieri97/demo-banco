package com.demo.banco.helpers.impl;

import com.demo.banco.contracts.response.UserResponse;
import com.demo.banco.helpers.DtoConverter;
import com.demo.banco.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class DtoConverterImpl implements DtoConverter {

    private final ObjectMapper mapper;

    public DtoConverterImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserResponse convertUserResponse(User user) {
        UserResponse response = mapper.convertValue(user, UserResponse.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy hh:mm:ss a");

        response.setPassword(user.getAuthInfo().getPassword());
        response.setToken(user.getAuthInfo().getToken());
        response.setCreated(formatter.format(user.getCreated()));
        if(user.getLastLogin() != null) response.setLastLogin(formatter.format(user.getLastLogin()));

        return response;
    }
}
