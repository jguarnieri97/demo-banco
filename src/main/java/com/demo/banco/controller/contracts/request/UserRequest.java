package com.demo.banco.controller.contracts.request;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private List<PhoneRequest> phones;

}
