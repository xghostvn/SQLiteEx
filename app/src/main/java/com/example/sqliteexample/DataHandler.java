package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class DataHandler extends SQLiteOpenHelper {

    private static final  int DATABASE_VERSION = 1;
    private static final  String DATABASE_NAME = "StudentsDB.db";
    public static final String TABLE_NAME = "Students";
    public static final  String COLUMN_ID = "StudentID";
    public static  final String COLUMN_NAME = "StudentName";

    public DataHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
        //create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String CREATE_STUDENTS_TABLE = "CREATE TABLE "
                    + TABLE_NAME + "("
                    +COLUMN_ID+" INTEGER PRIMARY KEY,"
                    + COLUMN_NAME +" TEXT )";

            sqLiteDatabase.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Xóa bảng nếu tồn tại
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //Tạo bảng mới
        onCreate(sqLiteDatabase);
    }
    public String LoadDataHandler(){
        String result = "";
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);


        while (cursor.moveToNext()){
                int student_ID = cursor.getInt(0);
                String student_Name = cursor.getString(1);

                result += String.valueOf(student_ID) +" " +student_Name
                        + System.getProperty("line.separator");
        }

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
}
