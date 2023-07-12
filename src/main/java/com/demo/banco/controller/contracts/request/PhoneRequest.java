package com.demo.banco.controller.contracts.request;

import lombok.Data;

@Data
public class PhoneRequest {
    private long number;
    private int citycode;
    private String countrycode;
}
