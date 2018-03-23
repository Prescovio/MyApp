package com.example.leon.myapp.Presenter;

import android.text.TextUtils;

import com.example.leon.myapp.Enumerations.ValidationErrorEnum;

/**
 * Created by Leon on 23.03.2018.
 */

public abstract class UserValidationPresenterBase extends PresenterBase {

    public ValidationErrorEnum validateFirstName(String firstName) {
        //first name empty
        if (TextUtils.isEmpty(firstName)) {
            return ValidationErrorEnum.Empty;
        }
        //first name invalid
        if (!this.isFirstNameValid(firstName)) {
            return ValidationErrorEnum.Invalid;
        }

        return ValidationErrorEnum.None;
    }

    public ValidationErrorEnum validateSecondName(String secondName) {
        //second name empty
        if (TextUtils.isEmpty(secondName)) {
            return ValidationErrorEnum.Empty;
        }
        //second name invalid
        if (!this.isSecondNameValid(secondName)) {
            return ValidationErrorEnum.Invalid;
        }

        return ValidationErrorEnum.None;
    }

    public ValidationErrorEnum validateAge(String age) {
        //second name empty
        if (TextUtils.isEmpty(age)) {
            return ValidationErrorEnum.Empty;
        }
        //second name invalid
        if (!this.isAgeValid(age)) {
            return ValidationErrorEnum.Invalid;
        }

        return ValidationErrorEnum.None;
    }

    public ValidationErrorEnum validateEmail(String email) {
        //password empty
        if (TextUtils.isEmpty(email)) {
            return ValidationErrorEnum.Empty;

        }
        //password invalid
        if (!this.isEmailValid(email)) {
            return ValidationErrorEnum.Invalid;
        }

        return ValidationErrorEnum.None;
    }

    public ValidationErrorEnum validatePassword(String password) {
        //password empty
        if (TextUtils.isEmpty(password)) {
            return ValidationErrorEnum.Empty;

        }
        //password invalid
        if (!this.isPasswordValid(password)) {
            return ValidationErrorEnum.Invalid;
        }

        return ValidationErrorEnum.None;
    }

    private boolean isFirstNameValid(String firstName) {
        return true;
    }

    private boolean isSecondNameValid(String secondName) {
        return true;
    }

    private boolean isAgeValid(String age) {
        return true;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
