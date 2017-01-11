package com.example.detailform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.name;
import static com.example.detailform.StudentInfo.TABLE;

/**
 * Created by yuyanozaki on 2017/01/09.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    //Context context;
   // SQLiteDatabase sqLiteDatabase;
    public static String DBNAME = "InsertSystem";

    public DataBaseHelper(Context context) {
        super(context, DBNAME, null, 4);
        //this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + StudentInfo.TABLE  + "("
                + StudentInfo.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + StudentInfo.KEY_NAME + " TEXT, "
                + StudentInfo.KEY_AGE + " INTEGER, "
                + StudentInfo.KEY_EMAIL + " TEXT )";

        //db.execSQL("CREATE TABLE UserInfo (id INTEGER AUTO_INCREMENT PRIMARY KEY,Name varchar(50),Email varchar(50),Age varchar(50))");
        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS"+StudentInfo.TABLE);
        //Create table again
        onCreate(db);
    }





  /*  public boolean inserts(String username, String useremail, String userage) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", username);
        cv.put("Email", useremail);
        cv.put("Age", userage);
        long result = sqLiteDatabase.insert("UserInfo",null,cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){

        sqLiteDatabase = this.getReadableDatabase();
        Cursor c =sqLiteDatabase.rawQuery("Select * from UserInfo",null);
        if(c != null)
        {
            c.moveToFirst();
        }
        return c;
    }

    public void update(String name,String email,String age){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name",name);
        cv.put("Email",email);
        cv.put("Age",age);
        sqLiteDatabase.update("UserInfo",cv,"Name = '"+name+"'",null);
    }

   public void delete(String username,String useremail,String userage) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("UserInfo","Name = '" + username + "'", null);
        sqLiteDatabase.delete("UserInfo","Email = '"+useremail+"'",null);
        sqLiteDatabase.delete("UserInfo","Age = '"+userage+"'",null);
    }
    */
}



