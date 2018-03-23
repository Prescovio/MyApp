package com.example.leon.myapp.Presenter.Login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.leon.myapp.Models.Database.UserDbHelper;
import com.example.leon.myapp.Models.Database.UserSchema;
import com.example.leon.myapp.Presenter.UserValidationPresenterBase;

public class LoginActivityPresenter extends UserValidationPresenterBase {
    private UserDbHelper dbHelper;
    private Context context;

    public LoginActivityPresenter(Context context) {
        //db helper
        this.context = context;
        dbHelper = new UserDbHelper(context);
    }

    /**
     * login called with user credentials
     * @param email
     * @param password
     * @return boolean
     */
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
}
