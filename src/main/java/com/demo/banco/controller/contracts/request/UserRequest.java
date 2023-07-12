package com.demo.banco.controller.contracts.request;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class UserRequest {

    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private List<PhoneRequest> phones;

}
