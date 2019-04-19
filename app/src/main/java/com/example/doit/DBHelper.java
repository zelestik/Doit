package com.example.doit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TODODB";
    public static final String TABLE_TODO = "TODO";

    public static final String KEY_ID = "_id";
    public static final String KEY_LESSON = "lesson";
    public static final String KEY_ALLLABS = "alllabs";
    public static final String KEY_DONELABS = "donelabs";
    public static final String KEY_DATE = "date";

    public DBHelper(@androidx.annotation.Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_TODO + "(" + KEY_ID + " integer primary key," + KEY_LESSON + " text," + KEY_ALLLABS + " integer," + KEY_DONELABS + " integer," + KEY_DATE + " integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
