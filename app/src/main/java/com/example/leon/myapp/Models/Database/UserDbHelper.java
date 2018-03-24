package com.example.leon.myapp.Models.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User.db";

    private static final String SQL_CREATE_ENTRIES =
                    "CREATE TABLE " +
                    UserSchema.UserEntry.TABLE_NAME + " (" +
                    UserSchema.UserEntry._ID         + " INTEGER PRIMARY KEY," +
                    UserSchema.UserEntry.FIRST_NAME  + " TEXT," +
                    UserSchema.UserEntry.SECOND_NAME + " TEXT, " +
                    UserSchema.UserEntry.AGE         + " INTEGER, " +
                    UserSchema.UserEntry.EMAIL       + " TEXT, " +
                    UserSchema.UserEntry.PASSWORD    + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserSchema.UserEntry.TABLE_NAME;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
