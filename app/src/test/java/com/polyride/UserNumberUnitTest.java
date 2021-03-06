package com.polyride;

import com.polyride.entity.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserNumberUnitTest {
    @Test
    public void numberSuccess() {
        User user = new User();
        user.setNumber("123456789");
        String number = user.getNumber();
        assertEquals("123456789", number);
    }

    @Test
    public void numberSuccess2() {
        User user = new User();
        user.setNumber("987654321");
        String number = user.getNumber();
        assertEquals("987654321", number);
    }
}
