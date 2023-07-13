package com.demo.banco.helpers;

import org.springframework.stereotype.Component;

public interface TokenService {

    String generateToken(String name, String email);

}
