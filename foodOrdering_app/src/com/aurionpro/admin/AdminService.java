package com.aurionpro.admin;


public class AdminService {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public boolean login(String user, String pass) {
        return USERNAME.equals(user) && PASSWORD.equals(pass);
    }
}
