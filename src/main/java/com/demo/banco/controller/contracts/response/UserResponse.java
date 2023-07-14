package com.demo.banco.controller.contracts.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class UserResponse {

    private String id;

    private String name;

    private String email;

    private String password;

    private String created;

    private String lastLogin;

    private String token;

    private boolean isActive;

    private List<PhoneResponse> phones;


}
