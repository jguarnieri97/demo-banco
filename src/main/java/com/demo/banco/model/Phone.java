package com.demo.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_PHONE")
@Getter
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private long number;

    private int citycode;

    private String countryCode;

    public Phone(User user, long number, int citycode, String countryCode) {
        this.user = user;
        this.number = number;
        this.citycode = citycode;
        this.countryCode = countryCode;
    }
}
