package com.demo.banco.model;

public class Phone {

    private long id;
    private long number;
    private int citycode;
    private String countryCode;

    public Phone(long number, int citycode, String countryCode) {
        this.number = number;
        this.citycode = citycode;
        this.countryCode = countryCode;
    }

}
