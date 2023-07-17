package com.demo.banco.helpers;

import com.demo.banco.contracts.request.PhoneRequest;
import com.demo.banco.contracts.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public static UserRequest createUserRequest(String email, String password) {
        PhoneRequest phoneRequest = new PhoneRequest(1155660088, 11, "AR");
        List<PhoneRequest> phoneList = new ArrayList<>();
        phoneList.add(phoneRequest);
        return new UserRequest("Jane Doe", email, password, phoneList);
    }

}
