package com.example.demo;

import com.example.demo.Model.User;
import com.example.demo.Model.enums.Role;
import com.example.demo.Model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void getSetUsername() {
        user.setUsername("username");
        assertEquals(user.getUsername(),"username");
        user.setUsername("");
        assertEquals(user.getUsername(),"");
    }

    @Test
    void getSetPassword() {
        user.setPassword("username");
        assertEquals(user.getPassword(),"username");
        user.setPassword("");
        assertEquals(user.getPassword(),"");
    }

    @Test
    void getSetEmail() {
        user.setEmail("username@yahoo.com");
        assertEquals(user.getEmail(),"username@yahoo.com");
        user.setEmail("");
        assertEquals(user.getEmail(),"");
    }

    @Test
    void getSetExpertise() {
        user.setExpertise(10);
        assertEquals(user.getExpertise(),10);
    }

    @Test
    void getSetSpeed() {
        user.setSpeed(10);
        assertEquals(user.getSpeed(),10);
    }

    @Test
    void getSetStatus() {
        Role role = Role.DEVELOPER;
        user.setRole(role);
        assertEquals(user.getRole(), role);
    }
}
