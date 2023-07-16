package com.demo.banco.service;

import com.demo.banco.controller.contracts.request.PhoneRequest;
import com.demo.banco.controller.contracts.request.UserRequest;
import com.demo.banco.exceptions.AuthNotFoundException;
import com.demo.banco.exceptions.RegisterUserException;
import com.demo.banco.exceptions.UserNotFoundException;
import com.demo.banco.helpers.Encrypter;
import com.demo.banco.helpers.TokenService;
import com.demo.banco.model.AuthInfo;
import com.demo.banco.model.User;
import com.demo.banco.persistance.AuthRepository;
import com.demo.banco.persistance.UserRepository;
import com.demo.banco.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public static final String LOGIN_TOKEN = "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserServiceImpl(userRepository, authRepository, encrypter, tokenService);
    }

    @Test
    void shouldRegisterNewUser() {
        UserRequest userRequest = createUserRequest("jdoe@example.com", "abcdefg1H2");

        when(encrypter.encryptPassword(userRequest.getPassword())).thenReturn("some password");
        when(tokenService.generateToken(userRequest.getName(), userRequest.getEmail()))
                .thenReturn("some token");

        userService.registerUser(userRequest);

        verify(userRepository).save(captor.capture());
        User user = captor.getValue();

        assertEquals(userRequest.getName(), user.getName());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getPhones().size(), user.getPhones().size());
        assertEquals(userRequest.getName(), user.getName());
        assertNotNull(user.getAuthInfo().getPassword());
        assertNotNull(user.getAuthInfo().getToken());
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

    @Test
    void whenRegisterUser_ifEmailNotValid_shouldThrowException() {
        UserRequest userRequest = createUserRequest("jdoeexample.com", "abcdefg1H2");

        assertThrows(RegisterUserException.class, () -> userService.registerUser(userRequest));
    }

    @Test
    void shouldGetUserByLoginToken() {
        User user = new User("Jane Doe", "jdoe@example.com", "abcdefg1H2", LocalDateTime.now(), true);
        user.setToken(LOGIN_TOKEN);
        AuthInfo authInfo = user.getAuthInfo();

        when(authRepository.findByToken(LOGIN_TOKEN)).thenReturn(Optional.of(authInfo));
        when(userRepository.findByAuthInfo(authInfo)).thenReturn(Optional.of(user));

        userService.loginUser(LOGIN_TOKEN);
        verify(tokenService).generateToken(user.getName(), user.getEmail());
        verify(userRepository).save(captor.capture());
        User response = captor.getValue();

        assertEquals(user, response);
    }

    @Test
    void whenLoginByToken_ifAuthInfoNotFound_shouldThrowException() {
        when(authRepository.findByToken(LOGIN_TOKEN))
                .thenReturn(Optional.empty());

        assertThrows(AuthNotFoundException.class, () -> userService.loginUser(LOGIN_TOKEN));
    }

    @Test
    void whenLoginByToken_ifUserNotFound_shouldThrowException() {
        AuthInfo authInfo = new AuthInfo();

        when(authRepository.findByToken(LOGIN_TOKEN))
                .thenReturn(Optional.of(authInfo));
        when(userRepository.findByAuthInfo(authInfo))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.loginUser(LOGIN_TOKEN));

    }

    private UserRequest createUserRequest(String email, String password) {
        PhoneRequest phoneRequest = new PhoneRequest(1155660088, 11, "AR");
        List<PhoneRequest> phoneList = new ArrayList<>();
        phoneList.add(phoneRequest);
        return new UserRequest("Jane Doe", email, password, phoneList);
    }


}