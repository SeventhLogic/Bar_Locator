package com.example.jen.bar_locator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Brock on 6/5/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "UserInfo.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "User_Table";
    public static final String COL_1 = "User_ID";
    public static final String COL_2 = "Password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(User_ID TEXT PRIMARY KEY, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertData(String User_ID, String Password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, User_ID);
        values.put(COL_2, Password);
        long result = db.insert(TABLE_NAME, null, values);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

}
