package com.demo.banco.controller.contracts.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponse {
    private long number;
    private int citycode;
    private String countrycode;
}
