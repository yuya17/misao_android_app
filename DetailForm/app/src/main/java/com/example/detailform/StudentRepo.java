package com.example.detailform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yuyanozaki on 2017/01/10.
 */

public class StudentRepo {

    private DataBaseHelper Helper;

    public StudentRepo(Context context){
        Helper = new DataBaseHelper(context);
    }

    public int insert(StudentInfo student) {//studentIDをreturn typeとして返しておくとdelete updateが楽になる

        //Open connection to write data
        SQLiteDatabase db = Helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(StudentInfo.KEY_AGE, student.age);
        cv.put(StudentInfo.KEY_EMAIL,student.email);
        cv.put(StudentInfo.KEY_NAME, student.name);

        // Inserting Row
        long student_ID = db.insert(StudentInfo.TABLE, null,cv);//return typeがintを返せないから
        db.close();// Closing database connection
        return (int) student_ID;
    }

    public void delete(int student_Id) {

        SQLiteDatabase db = Helper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        //StringArgsの部分は受け取ったID部分を選択できるようにしている。
        db.delete(StudentInfo.TABLE, StudentInfo.KEY_ID + "= ?", new String[] { String.valueOf(student_Id) });
        db.close(); // Closing database connection
    }

    public void update(StudentInfo student) {

        SQLiteDatabase db = Helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(student.KEY_AGE,student.age);
        cv.put(student.KEY_EMAIL,student.email);
        cv.put(student.KEY_NAME,student.name);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(StudentInfo.TABLE,cv,StudentInfo.KEY_ID + "= ?", new String[] { String.valueOf(student.student_ID) });
        db.close(); // Closing database connection
    }

    public StudentInfo getStudentById(int Id){
        SQLiteDatabase db = Helper.getReadableDatabase();
        String selectQuery =  "SELECT  "+StudentInfo.KEY_ID+","+StudentInfo.KEY_NAME+","+StudentInfo.KEY_EMAIL+","+StudentInfo.KEY_AGE+ " FROM "+ StudentInfo.TABLE
                + " WHERE " +
                StudentInfo.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        StudentInfo student = new StudentInfo();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                student.student_ID =cursor.getInt(cursor.getColumnIndex(StudentInfo.KEY_ID));
                student.name =cursor.getString(cursor.getColumnIndex(StudentInfo.KEY_NAME));
                student.email  =cursor.getString(cursor.getColumnIndex(StudentInfo.KEY_EMAIL));
                student.age =cursor.getInt(cursor.getColumnIndex(StudentInfo.KEY_AGE));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return student;
    }

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = Helper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                StudentInfo.KEY_ID + "," +
                StudentInfo.KEY_NAME + "," +
                StudentInfo.KEY_EMAIL + "," +
                StudentInfo.KEY_AGE +
                " FROM " + StudentInfo.TABLE;

       // StudentInfo student = new StudentInfo();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, null);//このnullは場所を指定しない。

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(StudentInfo.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(StudentInfo.KEY_NAME)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

}
