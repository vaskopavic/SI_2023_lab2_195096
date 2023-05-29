package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    SILab2 siLab2;

    @Test
    void testEveryBranch() {
        // user == null, allUsers = anything
        // 1-2, 2,18
        assertThrows(RuntimeException.class, () -> siLab2.function(null, new ArrayList<>()));

        // user.username == null, user.pass == 12345 , user.email = email@123.com, list = [diff, diff, same]
        // 1-3, 3-4, 4-5, 5-6.1, 6.1-6.2, (6.2-7, 7-9, 9-6.3, 6.3-6.2), 6.2-7, 7-8, 8-9, 9-10, 10-6.3, 6.3-6.2, 6.2-11, 11-12, 12-18
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("name1", "pass1", "mail1@.com"));
        userList.add(new User("name2", "pass2", "mail2@.com"));
        User user = new User(null, "12345", "email@123.com");
        userList.add(user);
        assertFalse(() -> siLab2.function(user, userList));

        //user.username = username, user.email = NONE,  user.pass = 123456789, list = [randomUser]
        // 1-3, 3-5, 5-11, 11-13, 13-14.1, 14.1-14.2, (14.2-15, 15-14.3, 14.3-14.2) 14.2-17, 17-18
        User userOne = new User("username", "123456789", "none");
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("randomUsername", "randomPassword", "randomEmail"));
        assertFalse(() -> siLab2.function(user, users));

        //user.password = 123 654321 else = []
        // 1-3, 3-5, 5-11, 11-13, 13-17, 17-18
        User newUser = new User("newUser", "123 654321", "email@mail.com");
        assertFalse(() -> siLab2.function(newUser, new ArrayList<>()));

        // user.password = 1234567#!, user.email = "none", user.username = username, allUsers = []
        // 1-3, 3-5, 5-11, 11-13, 13-14.1, 14.1-14.2, (14.2-15, 15-14.3, 14.3-14.2), 14.2-15, 15-16, 16-18
        User newUser2 = new User("username", "1234567#!", "none");
        assertFalse(() -> siLab2.function(newUser2, new ArrayList<>()));
    }

    @Test
    void testMultipleCondition() {
        // T X X; User == null, X, X
        assertThrows(RuntimeException.class, () -> siLab2.function(null, new ArrayList<>()));

        // F T X; User != null, user.password == null
        User user1 = new User("user1", null, null);
        assertThrows(RuntimeException.class, () -> siLab2.function(user1, new ArrayList<>()));

        // F F T; User != null, user.password != null && user.email == null
        User user2 = new User("user2", "pass2", null);
        assertThrows(RuntimeException.class, () -> siLab2.function(user2, new ArrayList<>()));

        // F F F; User != null, user.password != null && user.email != null
        User user3 = new User("user3", "pass3", "email3");
        assertFalse(() -> siLab2.function(user3, new ArrayList<>()));
    }
}