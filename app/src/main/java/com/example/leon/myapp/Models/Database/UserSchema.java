package com.example.leon.myapp.Models.Database;

import android.provider.BaseColumns;

public final class UserSchema {
    private UserSchema() {}

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String FIRST_NAME = "first_name";
        public static final String SECOND_NAME = "second_name";
        public static final String AGE = "age";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
    }
}
