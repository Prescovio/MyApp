package com.example.leon.myapp.Presenter.Registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.leon.myapp.Models.Database.UserDbHelper;
import com.example.leon.myapp.Models.Database.UserSchema;
import com.example.leon.myapp.Presenter.UserValidationPresenterBase;

public class RegistrationActivityPresenter extends UserValidationPresenterBase {
    private UserDbHelper dbHelper;
    private Context context;

    public RegistrationActivityPresenter(Context context) {
        //db helper
        this.context = context;
        dbHelper = new UserDbHelper(context);
    }

    //checks if the email is already taken
    public boolean userAvailable(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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

    //registers a new user
    public boolean register(String firstName, String secondName, int age, String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

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
