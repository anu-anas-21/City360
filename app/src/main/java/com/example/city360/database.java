package com.example.city360;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper
{
    public static final String dbname="users.db";
    public database(Context context) {
        super(context,"users.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDatabase)
    {
        String queryCreate="create table Collections(email text primary key,password text,pone number,user_type text)";
        MyDatabase.execSQL(queryCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int a, int b)
    {
        String drop="drop table if exists Collections";
        MyDatabase.execSQL(drop);
    }
    public boolean insertData(String email ,String password,String phone,String type)
    {
        SQLiteDatabase MyDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("email",email);
        cv.put("password",password);
        cv.put("phone",phone);
        cv.put("type",type);
        long result=MyDatabase.insert("Collections",null,cv);
        if (result==-1)
        {
            return false;
        } else {
            return true;
        }
    }
    public boolean checkMail(String email){
        SQLiteDatabase MyDatabase=this.getWritableDatabase();
        Cursor c=MyDatabase.rawQuery("select * from Collections where email = ?",new String[] {email});
        if(c.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkMailPassword(String email, String password){
        SQLiteDatabase MyDatabase=this.getWritableDatabase();
        Cursor c=MyDatabase.rawQuery("select * from Collections where email = ? and password = ?",new String[] {email,password});
        if(c.getCount()>0)
        {
            return true;
        } else {
            return false;
        }
    }
}
