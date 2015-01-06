package com.beardroid.morningbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Max on 1/5/2015.
 */
public class DataHandler extends SQLiteOpenHelper {
    //fields
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TIME = "time";
    public static final String KEY_IS_HEADLINE = "isHeadline";
    public static final String KEY_IS_ACTIVE = "isActive";
    public static final String KEY_DAYS = "days";
    public static final String KEY_MODULES = "modules";
    public static final String[] ALL_KEYS = new String[]{KEY_ROWID, KEY_TIME, KEY_IS_HEADLINE, KEY_IS_ACTIVE, KEY_DAYS, KEY_MODULES};

    //field numbers
    public static final int COL_ROWID = 0;
    public static final int COL_TIME = 1;
    public static final int COL_IS_HEADLINE = 2;
    public static final int COL_IS_ACTIVE = 3;
    public static final int COL_DAYS = 4;
    public static final int COL_MODULES = 5;

    public static final String TABLE_NAME = "mainTable";
    public static final String DATABASE_NAME = "AlarmDB";
    public static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBAdapter";
    private SQLiteDatabase db;

    //create db table
    public static final String TABLE_CREATE = "create table if not exists " + TABLE_NAME + " ("
            + KEY_ROWID + " integer primary key autoincrement,"
            + KEY_TIME + " text not null,"
            + KEY_IS_HEADLINE + " real not null,"
            + KEY_IS_ACTIVE + " real not null,"
            + KEY_DAYS + " text not null,"
            + KEY_MODULES + " text not null"
            + ");";

    public DataHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mainTable ");
        onCreate(db);
    }

    public DataHandler open(){
        db = getWritableDatabase();
        return this;
    }
    public void close(){
        close();
    }

    public long insertData(String time, int isHeadline, int isActive, String days, String modules) {
        ContentValues content = new ContentValues();
        content.put(KEY_TIME, time);
        content.put(KEY_IS_HEADLINE, isHeadline);
        content.put(KEY_IS_ACTIVE, isActive);
        content.put(KEY_DAYS, days);
        content.put(KEY_MODULES, modules);
        return  db.insert(TABLE_NAME, null, content);
    }

    public boolean deleteAlarm(long rowId){
        return db.delete(TABLE_NAME, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public void deleteAll(){
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
        if(c.moveToFirst()){
            do{
                deleteAlarm(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
    }

    public Cursor getAllRows(){
        Cursor c = db.query(true, TABLE_NAME, ALL_KEYS, null, null, null, null, null, null);
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }

    public boolean updateRow(long rowId, String time, int isHeadline, int isActive, String days, String modules) {
        String where = KEY_ROWID + "=" + rowId;
        ContentValues content = new ContentValues();
        content.put(KEY_TIME, time);
        content.put(KEY_IS_HEADLINE, isHeadline);
        content.put(KEY_IS_ACTIVE, isActive);
        content.put(KEY_DAYS, days);
        content.put(KEY_MODULES, modules);

        return db.update(TABLE_NAME, content, where, null) !=0;
    }
}
