package com.proxsoftware.webapp.entity;


import org.springframework.data.annotation.Id;

import java.math.BigInteger;

//@entity
//@Table(name = "users")
public class User {
    public static final int USERNAME_MIN_LENGTH = 5;
    public static final int USERNAME_MAX_LENGTH = 50;
    public static final String USERNAME_LENGTH_ERROR_MESSAGE = "Username must at least 5 characters.";
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 50;
    public static final String PASSWORD_LENGTH_ERROR_MESSAGE = "Password must at least 8 characters.";

//    @GeneratedValue
        @Id
    private BigInteger contentId;
//    private BigInteger contentId;

//    @Pattern(regexp = "^[A-Za-z0-9]*$")
//    @NotNull
//    @Size(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = USERNAME_LENGTH_ERROR_MESSAGE)

    private String username;

//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
//    @NotNull
//    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = PASSWORD_LENGTH_ERROR_MESSAGE)
    private String password;

    public User() { }

    @Override
    public String toString() {
        return "User{" +
                "contentId=" + contentId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(BigInteger contentId, String username, String password) {
        this.contentId = contentId;
        this.username = username;
        this.password = password;
    }

    public BigInteger getId() {

        return contentId;
    }

    public void setId(BigInteger contentId) {
        this.contentId = contentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}