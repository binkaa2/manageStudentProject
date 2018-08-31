package com.example.bin.managestudentproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.bin.managestudentproject.bean.studentsBean;

import java.util.ArrayList;

public class studentsDao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "managesinhvien";
    private static final String TABLE_NAME = "students";
    private static final int SQL_VERSION = 1;


    public studentsDao(Context context) {
        super(context,DATABASE_NAME,null,SQL_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_users_table = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(drop_users_table);
        onCreate(db);
    }

    public void ganGiaTriMacDinh(){
        int count = this.countStudents();
        if(count == 0){
            studentsBean student = new studentsBean();
            student.setHoten("Nguyễn Văn Thành");
            student.setLop("K40A");
            student.setChuyenNganh("Phần Mềm CNTT");
            student.setSdt("01678869933");
            this.addStudent(student);
        }
    }

    public void addStudent(studentsBean student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",student.getHoten());
        values.put("lop",student.getLop());
        values.put("chuyennganh",student.getChuyenNganh());
        values.put("sdt",student.getSdt());
        values.put("anhdaidien",student.getAnhDaiDien());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int countStudents(){
        SQLiteDatabase database = this.getReadableDatabase();
        int count;
        Cursor cursor = database.rawQuery("SELECT * from "+TABLE_NAME+";",null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }


    public ArrayList<studentsBean> getAllStudents(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+";",null);
        studentsBean student;
        ArrayList<studentsBean> listStudents = new ArrayList<studentsBean>();
        if(cursor != null){
            while(cursor.moveToNext()){
                student = new studentsBean();
                student.setId(cursor.getInt(0));
                student.setHoten(cursor.getString(1));
                student.setLop(cursor.getString(2));
                student.setChuyenNganh(cursor.getString(3));
                student.setSdt(cursor.getString(4));
                student.setAnhDaiDien(cursor.getBlob(5));
                listStudents.add(student);
            }
            cursor.close();
            db.close();
            return listStudents;
        }
        return null;
    }

    public void deleteStudents(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"id = ?",new String[]{String.valueOf(id)});
    }


}
