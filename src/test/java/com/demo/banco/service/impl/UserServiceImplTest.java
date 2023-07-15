package com.demo.banco.service.impl;

import com.demo.banco.controller.contracts.request.PhoneRequest;
import com.demo.banco.controller.contracts.request.UserRequest;
import com.demo.banco.exceptions.RegisterUserException;
import com.demo.banco.helpers.Encrypter;
import com.demo.banco.helpers.TokenService;
import com.demo.banco.model.User;
import com.demo.banco.persistance.AuthRepository;
import com.demo.banco.persistance.UserRepository;
import com.demo.banco.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthRepository authRepository;
    @Mock
    private Encrypter encrypter;
    @Mock
    private TokenService tokenService;
    @Captor
    private ArgumentCaptor<User> captor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserServiceImpl(userRepository, authRepository, encrypter, tokenService);
    }

    @Test
    void shouldRegisterNewUser() {
        UserRequest userRequest = createUserRequest("jdoe@example.com", "abcdefg1H2");

        userService.registerUser(userRequest);

        verify(userRepository).save(captor.capture());
        User user = captor.getValue();

        assertEquals(userRequest.getName(), user.getName());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getPhones().size(), user.getPhones().size());
        assertEquals(userRequest.getName(), user.getName());
    }

    @Test
    void whenRegisterUser_ifPasswordShort_shouldThrowException() {
        UserRequest userRequest = createUserRequest("jdoe@example.com", "abc12F");

        assertThrows(RegisterUserException.class, () -> userService.registerUser(userRequest));
    }

    @Test
    void whenRegisterUser_ifPasswordLong_shouldThrowException() {
        UserRequest userRequest = createUserRequest("jdoe@example.com", "abcdefg1H2abcdfgre");

        assertThrows(RegisterUserException.class, () -> userService.registerUser(userRequest));
    }

    @Test
    void whenRegisterUser_ifPwNotHaveTwoNumbers_shouldThrowException() {
        UserRequest userRequest = createUserRequest("jdoe@example.com", "abcdefgH1");

        assertThrows(RegisterUserException.class, () -> userService.registerUser(userRequest));
    }

    @Test
    void whenRegisterUser_ifPwNotHaveCapitalLetter_shouldThrowException() {
        UserRequest userRequest = createUserRequest("jdoe@example.com", "abcdefgh12");

        assertThrows(RegisterUserException.class, () -> userService.registerUser(userRequest));
    }

    private UserRequest createUserRequest(String email, String password) {
        PhoneRequest phoneRequest = new PhoneRequest(1155660088, 11, "AR");
        List<PhoneRequest> phoneList = new ArrayList<>();
        phoneList.add(phoneRequest);
        return new UserRequest("Jane Doe", email, password, phoneList);
    }

}