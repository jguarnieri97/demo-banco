package com.demo.banco.service.impl;

import com.demo.banco.controller.contracts.request.UserRequest;
import com.demo.banco.exceptions.AuthNotFoundException;
import com.demo.banco.exceptions.RegisterUserException;
import com.demo.banco.exceptions.UserAlreadyExistException;
import com.demo.banco.exceptions.UserNotFoundException;
import com.demo.banco.helpers.Encrypter;
import com.demo.banco.helpers.TokenService;
import com.demo.banco.model.AuthInfo;
import com.demo.banco.model.Phone;
import com.demo.banco.model.User;
import com.demo.banco.persistance.AuthRepository;
import com.demo.banco.persistance.UserRepository;
import com.demo.banco.service.UserService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final Encrypter encrypter;
    private final TokenService tokenService;
    public static final boolean IS_ACTIVE = true;

    public UserServiceImpl(UserRepository repository, AuthRepository authRepository, Encrypter encrypter, TokenService tokenService) {
        this.userRepository = repository;
        this.authRepository = authRepository;
        this.encrypter = encrypter;
        this.tokenService = tokenService;
    }

    /**
     * Register new User
     *
     * @param request the user to be stored
     * @return user created
     */
    @Override
    public User registerUser(UserRequest request) {

        if (!isValidEmail(request.getEmail())) throw new RegisterUserException("Incorrect email");
        if (!isValidPassword(request.getPassword())) throw new RegisterUserException("Incorrect password");
        if (checkIfUserExist(request.getEmail())) throw new UserAlreadyExistException(request.getEmail());

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

        user.setToken(tokenService.generateToken(user.getName(), user.getEmail()));

        return userRepository.save(user);
    }

    /**
     * Login user with token
     * @param token valid access token
     * @return user found
     */
    @Override
    public User loginUser(String token) {
        AuthInfo authInfo = authRepository.findByToken(token)
                .orElseThrow(AuthNotFoundException::new);
        User user = userRepository.findByAuthInfo(authInfo)
                .orElseThrow(UserNotFoundException::new);

        user.setToken(tokenService.generateToken(user.getName(), user.getEmail()));
        user.setLastLogin(ZonedDateTime.now());

        return userRepository.save(user);
    }

    /**
     * Validate in RFC-5322
     *
     * @param email The email to validate
     * @return if is valid return true
     */
    private boolean isValidEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", email);
    }

    /**
     * Validate password with only one capital, two numbers and size between 8-12
     *
     * @param password The password to validate
     * @return if is valid return true
     */
    private boolean isValidPassword(String password) {
        return Pattern.matches("^(?=(.*[a-z])*)(?=(.*[A-Z]))(?=(.*\\d){2}).{8,12}+$", password);
    }

    /**
     * Check if exist user persisted in db
     * @param email to find user
     * @return true if exist
     */
    private boolean checkIfUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

}
