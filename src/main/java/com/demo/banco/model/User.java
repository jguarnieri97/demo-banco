package com.demo.banco.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class User extends SystemUser{

    private String name;
    private String email;
    private List<Phone> phones;

    public User(String password, ZonedDateTime created, boolean isActive, String name, String email, List<Phone> phones) {
        super(password, created, isActive);
        this.name = name;
        this.email = email;
        this.phones = phones;
    }
}
