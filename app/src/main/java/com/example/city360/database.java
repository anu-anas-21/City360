package com.example.city360;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper
{
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public database(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String queryCreate="create table Collections(email text,password text,pone number)";
        sqLiteDatabase.execSQL(queryCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int a, int b)
    {

    }
    public void register(String email,String password,String phone)
    {
        ContentValues cv=new ContentValues();
        cv.put("email",email);
        cv.put("password",password);
        cv.put("phone",phone);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("Customer",null,cv);
        db.close();
    }
}
