package com.demo.banco.controller.contracts.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String id;

    private String name;

    private String email;

    private String password;

    private ZonedDateTime created;

    private ZonedDateTime lastLogin;

    private String token;

    private boolean isActive;

    private List<PhoneResponse> phones;


}
