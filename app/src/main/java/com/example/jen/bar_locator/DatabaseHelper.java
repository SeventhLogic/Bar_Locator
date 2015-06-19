package com.example.jen.bar_locator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

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

    public boolean hasObject(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_NAME + "WHERE " + COL_1 + " =?";

        Cursor cursor = db.rawQuery(selectString, new String[] {COL_1});

        boolean hasObject = false;
        if(cursor.moveToFirst())
        {
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while (cursor.moveToNext())
            {
                count++;
            }

            //Log.d(Tag, String.format("%d records found", count));
        }

        cursor.close();
        db.close();
        return hasObject;
    }
}
