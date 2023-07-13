package com.demo.banco.helpers.impl;

import com.demo.banco.helpers.TokenService;
import com.demo.banco.helpers.UserHelper;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenServiceImpl implements TokenService {


    @Override
    public String generateToken(String name, String email) {
        return Jwts.builder()
                .claim("name", name)
                .claim("email", email)
                .setSubject(UserHelper.generateUsername(name))
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .compact();
    }
}
