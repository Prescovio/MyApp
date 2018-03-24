package com.example.leon.myapp.Models.Registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.leon.myapp.Models.Database.UserDbHelper;
import com.example.leon.myapp.Models.Database.UserSchema;

/**
 * Created by Leon on 24.03.2018.
 */

public class RegistrationActivityModel {
    private UserDbHelper mDbHelper;

    public RegistrationActivityModel(Context context) {
        this.mDbHelper = new UserDbHelper(context);
    }

    /**
     * checks if the email is already taken
     * @param email
     * @return boolean
     */
    public boolean userAvailable(String email) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = { UserSchema.UserEntry.EMAIL };
        String selection = UserSchema.UserEntry.EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(
                UserSchema.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int rows = cursor.getCount();
        cursor.close();

        if (rows == 0)
            return true;
        else
            return false;
    }

    /**
     * registers a new user
     * @param firstName
     * @param secondName
     * @param age
     * @param email
     * @param password
     * @return boolean
     */
    public boolean register(String firstName, String secondName, int age, String email, String password) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserSchema.UserEntry.FIRST_NAME, firstName);
        values.put(UserSchema.UserEntry.SECOND_NAME, secondName);
        values.put(UserSchema.UserEntry.AGE, age);
        values.put(UserSchema.UserEntry.EMAIL, email.toLowerCase());
        values.put(UserSchema.UserEntry.PASSWORD, password);

        long newRowId = db.insert(UserSchema.UserEntry.TABLE_NAME, null, values);

        return true;
    }
}
