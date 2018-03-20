package com.example.leon.myapp.Models.Helper;

public final class UserValidation {
    private UserValidation() {}

    public static boolean isFirstNameValid(String firstName) {
        return true;
    }

    public static boolean isSecondNameValid(String secondName) {
        return true;
    }

    public static boolean isAgeValid(String age) {
        return true;
    }

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
