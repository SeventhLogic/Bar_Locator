package com.example.jen.bar_locator;

import android.database.Cursor;

/**
 * Created by Matt on 5/14/2015.
 */
public class ErrorCheck {
    DatabaseHelper myDb;

    //String sql = "SELECT * FROM User_Info WHERE UserID = '" + tempxval + "'";
    //Cursor data = database.rawQuery(sql, null);

    //if (cursor.moveToFirst()) {
    // record exists
    // } else {
    // record not found
    // }




    public String checkNull(String tempxval){
        String stringCheck;
        stringCheck = tempxval;
        if(stringCheck.matches("")){
            return "N";
        }
        return "G";
    }

    public String checkUserID (String tempxval){

        return "F";
    }

    public String checkPassword (String tempxval){

        return "F";

    }



}
