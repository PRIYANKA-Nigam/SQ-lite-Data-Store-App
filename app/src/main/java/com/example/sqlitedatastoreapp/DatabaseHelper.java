package com.example.sqlitedatastoreapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1); }@Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(name text,password number)"); }@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS user"); }
    public  boolean insert(String name,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("password",password);
        long ins=db.insert("user",null,contentValues);
        if(ins==-1) return  false;
        else return  true; }
    public boolean check(String name){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from user where name=?",new String[] {name});
        if(cursor.getCount()>0)
            return false;
        else
            return true; }
    public boolean namePass(String s1,String s2){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from user where name=? and password=?",new String[] {s1,s2});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Cursor Display(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM user",null);
        return c;
    }
public Integer deleteData(String name){
    SQLiteDatabase db=this.getReadableDatabase();
return db.delete("user","name= ?",new String[] {name});
}

}
