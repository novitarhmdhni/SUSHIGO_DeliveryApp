package com.example.sushigo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Dbase_SushiGo extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_LOGIN";
    public Dbase_SushiGo(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbase) {
        dbase.execSQL("CREATE TABLE session (id integer PRIMARY KEY, login text)");
        dbase.execSQL("CREATE TABLE user (id integer PRIMARY KEY AUTOINCREMENT, email text, password text)");
        dbase.execSQL("INSERT INTO session(id, login) VALUES (1, 'kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbase, int i, int i1) {
        dbase.execSQL("DROP TABLE IF EXISTS session");
        dbase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(dbase);
    }

    //check session
    public Boolean checkSession(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ? ", new String[]{value});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String value, int id){
        SQLiteDatabase dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("login", value);
        long update = dbase.update("session", values, "id= " +id, null);
        if (update == -1){
            return false;
        }else {
            return true;
        }
    }

    //input user
    public boolean simpanUser(String email, String password){
        SQLiteDatabase dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        long insert = dbase.insert("user", null, values);
        if (insert == -1){
            return false;
        }else {
            return true;
        }
    }

    //check login
    public Boolean checkLogin(String email, String password){
        SQLiteDatabase dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("SELECT * FROM user WHERE  email = ? AND password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}
