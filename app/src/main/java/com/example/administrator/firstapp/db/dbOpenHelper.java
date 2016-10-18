package com.example.administrator.firstapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/20.
 */
public class dbOpenHelper extends SQLiteOpenHelper {
    public static final String Tb_schedule = "create table Tb_schedule ("
            + "id integer primary key autoincrement, "
            + "week String, "
            +"whichLesson String"
            +"cName String"
            +"period datetime"+
            "startTime text"
            +"endTime text"
           + "address String"
            +"remindTime String"
            +"isRemindByVibrato String"
            +"isRemindByRing String"
            +"teacher String"
            +" submitDate String)";
    public static final String Tb_plan="create table Tb_plan ("
            + "id integer primary key autoincrement, "
            + "pno String, "
            +"content String"
            +"remindTime String"
            +"isRemind datetime"
            +"isRemindByVibrato String"
            +"isRemindByRing String)";
    public dbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(Tb_schedule);
        db.execSQL(Tb_plan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
