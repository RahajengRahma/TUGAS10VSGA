package com.example.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRTSNAME = "name";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + TABLE_STUDENTS + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRTSNAME + "TEXT);";

    public DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(sqLiteDatabase);

    }

    public long addStudentDetail (String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRTSNAME, student);
        long insert = db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return insert;
    }

    public ArrayList<String> getAllStudentList(){
        ArrayList<String> studentArrayList = new ArrayList<>();
        String name="";
        String selectQuery = "SELECT * FROM "+ TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(KEY_FIRTSNAME));
                studentArrayList.add(name);
            }while (c.moveToNext());
        }
        return studentArrayList;
    }

}
