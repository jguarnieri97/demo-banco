package com.demo.banco.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRequest {
    private long number;
    private int citycode;
    private String countrycode;
}
