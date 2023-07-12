package com.demo.banco.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_PHONE")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private long number;
    private int citycode;
    private String countryCode;

    public Phone() { }

    public Phone(long number, int citycode, String countryCode) {
        this.number = number;
        this.citycode = citycode;
        this.countryCode = countryCode;
    }
}
