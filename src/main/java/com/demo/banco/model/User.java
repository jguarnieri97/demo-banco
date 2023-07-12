package com.demo.banco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "BANK_USER")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@NoArgsConstructor
public class User extends SystemUser{

    private String name;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;

    public User(String password, ZonedDateTime created, boolean isActive, String name, String email, List<Phone> phones) {
        super(password, created, isActive);
        this.name = name;
        this.email = email;
        this.phones = phones;
    }
}
