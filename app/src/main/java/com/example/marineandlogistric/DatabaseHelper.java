package com.example.marineandlogistric;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MarineDB.db";
    private static final int DATABASE_VERSION = 2;

    // ===================== SHIP TABLE =====================

    public static final String TABLE_SHIPS = "ships";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_IMO = "imo";
    public static final String COL_TYPE = "type";
    public static final String COL_FLAG = "flag";
    public static final String COL_STATUS = "status";

    // ===================== CREW TABLE =====================

    public static final String TABLE_CREW = "crew";

    public static final String COL_CREW_ID = "crew_id";
    public static final String COL_CREW_NAME = "crew_name";
    public static final String COL_RANK = "rank";
    public static final String COL_NATIONALITY = "nationality";
    public static final String COL_PASSPORT = "passport";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Ship Table
        db.execSQL("CREATE TABLE " + TABLE_SHIPS + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT," +
                COL_IMO + " TEXT," +
                COL_TYPE + " TEXT," +
                COL_FLAG + " TEXT," +
                COL_STATUS + " TEXT)");

        // Crew Table
        db.execSQL("CREATE TABLE " + TABLE_CREW + "(" +
                COL_CREW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_CREW_NAME + " TEXT," +
                COL_RANK + " TEXT," +
                COL_NATIONALITY + " TEXT," +
                COL_PASSPORT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHIPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREW);

        onCreate(db);
    }

    // ===================== SHIP METHODS =====================

    public boolean insertShip(String name,
                              String imo,
                              String type,
                              String flag,
                              String status) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_NAME, name);
        values.put(COL_IMO, imo);
        values.put(COL_TYPE, type);
        values.put(COL_FLAG, flag);
        values.put(COL_STATUS, status);

        long result = db.insert(TABLE_SHIPS, null, values);

        return result != -1;
    }

    public Cursor getAllShips() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_SHIPS, null);
    }

    public boolean deleteShip(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_SHIPS,
                COL_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }

    // ===================== CREW METHODS =====================

    public boolean insertCrew(String name,
                              String rank,
                              String nationality,
                              String passport) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_CREW_NAME, name);
        values.put(COL_RANK, rank);
        values.put(COL_NATIONALITY, nationality);
        values.put(COL_PASSPORT, passport);

        long result = db.insert(TABLE_CREW, null, values);

        return result != -1;
    }

    public Cursor getAllCrew() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_CREW, null);
    }
}