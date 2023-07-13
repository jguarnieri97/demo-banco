package com.demo.banco.service.impl;

import com.demo.banco.controller.contracts.request.UserRequest;
import com.demo.banco.exceptions.RegisterUserException;
import com.demo.banco.helpers.Encrypter;
import com.demo.banco.helpers.TokenService;
import com.demo.banco.model.Phone;
import com.demo.banco.model.User;
import com.demo.banco.persistance.UserRepository;
import com.demo.banco.service.UserService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final Encrypter encrypter;
    private final TokenService tokenService;
    public static final boolean IS_ACTIVE = true;

    public UserServiceImpl(UserRepository repository, Encrypter encrypter, TokenService tokenService) {
        this.repository = repository;
        this.encrypter = encrypter;
        this.tokenService = tokenService;
    }

    @Override
    public User registerUser(UserRequest request) {

        if(!isValidEmail(request.getEmail())) throw new RegisterUserException("Incorrect email");
        if(!isValidPassword(request.getPassword())) throw new RegisterUserException("Incorrect password");

        User user = new User(
                request.getName(),
                request.getEmail(),
                encrypter.encryptPassword(request.getPassword()),
                ZonedDateTime.now(),
                IS_ACTIVE
        );

        request.getPhones().forEach(phone -> {
            user.addPhone(new Phone(user, phone.getNumber(), phone.getCitycode(), phone.getCountrycode()));
        });

        //setToken

        return repository.save(user);
    }

    /**
     * Validate in RFC-5322
     * @param email The email to validate
     * @return if is valid return true
     */
    private boolean isValidEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", email);
    }

    /**
     * Validate password with only one capital, two numbers and size between 8-12
     * @param password The password to validate
     * @return if is valid return true
     */
    private boolean isValidPassword(String password) {
        return Pattern.matches("^(?=(.*[a-z])*)(?=(.*[A-Z]))(?=(.*\\d){2}).{8,12}+$", password);
    }

}
