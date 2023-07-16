package com.demo.banco.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldConstructUserProperly() {
        String name = "Jane Doe";
        String mail = "jdoe@example.com";
        String password = "abcdef1H2";
        LocalDateTime now = LocalDateTime.now();
        boolean isActive = true;

        User user = new User(name, mail, password, now, isActive);

        assertEquals(name, user.getName());
        assertEquals(mail, user.getEmail());
        assertNotNull(user.getAuthInfo());
        assertEquals(password, user.getAuthInfo().getPassword());
        assertEquals(now, user.getCreated());
        assertNull(user.getLastLogin());
        assertTrue(user.isActive());
    }

    @Test
    void shouldAddNewPhone() {
        User user = new User();
        int number = 115566;
        int citycode = 11;
        String countryCode = "AR";

        Phone phone = new Phone(user, number, citycode, countryCode);
        user.addPhone(phone);

        assertEquals(1, user.getPhones().size());
        assertEquals(phone, user.getPhones().get(0));
    }

}