package com.demo.banco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BANK_USER")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@NoArgsConstructor
public class User extends SystemUser {

    private String name;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;

    public User(String name, String email, String password, LocalDateTime created, boolean isActive) {
        super(password, created, isActive);
        this.name = name;
        this.email = email;
        this.phones = new ArrayList<>();
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

}
