package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

// DataHandler class  = db --- each class = 1 db
public class DataHandler extends SQLiteOpenHelper {

    private static final  int DATABASE_VERSION = 1;
    private static final  String DATABASE_NAME = "StudentsDB.db";
    public static final String TABLE_NAME = "Students";
    public static final  String COLUMN_ID = "StudentID";
    public static  final String COLUMN_NAME = "StudentName";
    public static String TAG = "abc";

    public DataHandler(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }
        //create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: " + TABLE_NAME );
            String CREATE_STUDENTS_TABLE = "CREATE TABLE "
                    + TABLE_NAME + "("
                    +COLUMN_ID+" INTEGER PRIMARY KEY,"
                    + COLUMN_NAME +" TEXT )";

            sqLiteDatabase.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
        //Xóa bảng nếu tồn tại
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //Tạo bảng mới
        onCreate(sqLiteDatabase);
    }
    public String LoadDataHandler(){
        String result = "";
        String query = "SELECT* FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Log.d(TAG, "LoadDataHandler: "+cursor.getCount());
         cursor.moveToFirst();

        do{
            int student_ID = cursor.getInt(0);
            String student_Name = cursor.getString(1);
            Log.d(TAG, "LoadDataHandler: " + student_ID +" "+ student_Name);
            result += String.valueOf(student_ID) +" " +student_Name
                    + System.getProperty("line.separator");

        }while (cursor.moveToNext());



        cursor.close();
        db.close();

        return  result;

    }

    public void addDataStudent(Student student){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,student.getName());
        values.put(COLUMN_ID,student.getID());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public String findDataHandler(int ID){
            String result = "";
        String query = "SELECT * FROM "
                +TABLE_NAME +" WHERE "
                +COLUMN_ID +" = " + ID;

        SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(query,null);

       if(cursor.moveToFirst()){
           do{
               int Name_ID = cursor.getInt(0);
               String Name_student = cursor.getString(1);
               result += Name_ID+" " +Name_student + System.getProperty("line.separator");

           }while (cursor.moveToNext());
       }

       cursor.close();
       db.close();

       return  result;
    }

    public boolean deleteStudent(int ID){
        boolean result = false;
        String query = "SELECT * FROM "
                +TABLE_NAME +" WHERE "
                +COLUMN_ID +" ='"
                +String.valueOf(ID) +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            db.delete(TABLE_NAME,COLUMN_ID + "=?",new String[]{String.valueOf(ID)});
            result = true;
        }

        return result;

    }


}
