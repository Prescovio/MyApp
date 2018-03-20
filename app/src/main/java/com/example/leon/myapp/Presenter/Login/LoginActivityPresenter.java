package com.example.leon.myapp.Presenter.Login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.leon.myapp.Models.Database.UserDbHelper;
import com.example.leon.myapp.Models.Database.UserSchema;
import com.example.leon.myapp.Models.Helper.UserValidation;
import com.example.leon.myapp.R;

public class LoginActivityPresenter {
    private UserDbHelper dbHelper;
    private Context context;

    public LoginActivityPresenter(Context context) {
        //db helper
        this.context = context;
        dbHelper = new UserDbHelper(context);
    }

    //perform login
    public boolean login(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                UserSchema.UserEntry.EMAIL,
                UserSchema.UserEntry.PASSWORD
        };
        String selection = UserSchema.UserEntry.EMAIL.toLowerCase() + " = ? AND "
                + UserSchema.UserEntry.PASSWORD + " = ?";

        String[] selectionArgs = { email, password};

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
            return false;
        else
            return true;
    }

    public String validateEmail(String email) {
        String error = "";

        //password empty
        if (TextUtils.isEmpty(email)) {
            error = context.getString(R.string.error_field_required);

        }
        //password invalid
        if (!UserValidation.isEmailValid(email)) {
            error = context.getString(R.string.error_invalid_email);
        }

        return error;
    }

    public String validatePassword(String password) {
        String error = "";

        //password empty
        if (TextUtils.isEmpty(password)) {
            error = context.getString(R.string.error_field_required);

        }
        //password invalid
        if (!UserValidation.isPasswordValid(password)) {
            error = context.getString(R.string.error_invalid_password);
        }

        return error;
    }
}
