package com.demo.banco.helpers.impl;

import com.demo.banco.helpers.Encrypter;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class EncrypterImpl implements Encrypter {

    @Override
    public String encryptPassword(String pw) {
        return Hashing.sha256()
                .hashString(pw, StandardCharsets.UTF_8)
                .toString();
    }
}
