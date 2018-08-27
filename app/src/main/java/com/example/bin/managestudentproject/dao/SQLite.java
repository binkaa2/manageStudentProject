package com.example.bin.managestudentproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bin.managestudentproject.bean.usersBean;

import java.util.ArrayList;

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "managesinhvien";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*//truy van khong tra ket qua
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //truy van co tra ket qua
    private Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql);
    }*/



    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users_table = "CREATE TABLE " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY," +
                "username TEXT," +
                "password TEXT," +
                "email TEXT);";
        db.execSQL(create_users_table);
        create_users_table = "CREATE TABLE " + "students" + "(" +
                "id INTEGER NOT NULL PRIMARY KEY," +
                "hoten TEXT NOT NULL," +
                "lop TEXT NOT NULL," +
                "chuyennganh TEXT NOT NULL," +
                "sdt TEXT," +
                "anhdaidien BLOB);";
        db.execSQL(create_users_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_users_table = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(drop_users_table);
        onCreate(db);
    }

    public usersBean createDefaultUser(){
        int count = this.countUsers();
        if(count == 0){
            usersBean user = new usersBean();
            user.setUsername("binkaa2");
            user.setPassword("123456");
            user.setEmail("binkaa1@gmail.com");
            this.addUsers(user);
        }
        return null;
    }

    public void addUsers(usersBean users){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",users.getUsername());
        values.put("password",users.getPassword());
        values.put("email",users.getEmail());
        database.insert(TABLE_NAME,null,values);
        database.close();
    }

    public ArrayList<usersBean> getAllUsers(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * from "+TABLE_NAME,null);
        usersBean usersBean;
        ArrayList<usersBean> list = new ArrayList<usersBean>();
        while(cursor.moveToNext()){
            usersBean = new usersBean();
            usersBean.setId(cursor.getInt(0));
            usersBean.setUsername(cursor.getString(1));
            usersBean.setPassword(cursor.getString(2));
            usersBean.setEmail(cursor.getString(3));
            list.add(usersBean);
        }
        cursor.close();
        database.close();
        return list;
    }

    public boolean checkLogin(String username,String password){
        SQLiteDatabase database = this.getReadableDatabase();
        //selection
        String[] columns = {"id"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username,password};
        Cursor cursor = null;
        try{
            cursor = database.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            cursor.moveToFirst();
            int i = cursor.getCount();
            cursor.close();
            if(i<=0)
                return false;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public usersBean getUser(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, "id" + " = ?", new String[] { String.valueOf(id) },null, null, null);
        //Cursor cursor = database.query(TABLE_NAME,null,"id" + "= ?",new String[]{String.valueOf(id),null,null,null});
        //Cursor cursor = database.rawQuery("select * from "+TABLE_NAME+" where id = '"+String.valueOf(id)+"';",null);
        if(cursor!=null) {
            cursor.moveToFirst();
            usersBean users = new usersBean();
            users.setId(cursor.getInt(0));
            users.setUsername(cursor.getString(1));
            users.setPassword(cursor.getString(2));
            users.setEmail(cursor.getString(3));
            return users;
        }
        return null;
    }

    public int countUsers(){
        SQLiteDatabase database = this.getReadableDatabase();
        int count;
        Cursor cursor = database.rawQuery("SELECT * from "+TABLE_NAME,null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }

}
