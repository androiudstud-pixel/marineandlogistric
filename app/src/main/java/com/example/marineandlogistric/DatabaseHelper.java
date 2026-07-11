package com.example.marineandlogistric;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MarineDB.db";
    private static final int DATABASE_VERSION = 4;

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
    public static final String COL_SHIP_ID = "ship_id";

    // ===================== EQUIPMENT TABLE =====================

    public static final String TABLE_EQUIPMENT = "equipment";
    public static final String COL_EQ_ID = "eq_id";
    public static final String COL_EQ_NAME = "eq_name";
    public static final String COL_EQ_TYPE = "eq_type";
    public static final String COL_EQ_SERIAL = "eq_serial";
    public static final String COL_EQ_SHIP_ID = "eq_ship_id";

    // ===================== CERTIFICATES TABLE =====================

    public static final String TABLE_CERTIFICATES = "certificates";
    public static final String COL_CERT_ID = "cert_id";
    public static final String COL_CERT_NAME = "cert_name";
    public static final String COL_CERT_ISSUE = "issue_date";
    public static final String COL_CERT_EXPIRY = "expiry_date";
    public static final String COL_CERT_SHIP_ID = "cert_ship_id";

    // ===================== REPORTS TABLE =====================

    public static final String TABLE_REPORTS = "reports";
    public static final String COL_REPORT_ID = "report_id";
    public static final String COL_REPORT_TITLE = "report_title";
    public static final String COL_REPORT_DATE = "report_date";
    public static final String COL_REPORT_CONTENT = "report_content";
    public static final String COL_REPORT_SHIP_ID = "report_ship_id";
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
                COL_SHIP_ID + " INTEGER," +
                COL_CREW_NAME + " TEXT," +
                COL_RANK + " TEXT," +
                COL_NATIONALITY + " TEXT," +
                COL_PASSPORT + " TEXT)");

        // Equipment Table
        db.execSQL("CREATE TABLE " + TABLE_EQUIPMENT + "(" +
                COL_EQ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_EQ_SHIP_ID + " INTEGER," +
                COL_EQ_NAME + " TEXT," +
                COL_EQ_TYPE + " TEXT," +
                COL_EQ_SERIAL + " TEXT)");

        // Certificates Table
        db.execSQL("CREATE TABLE " + TABLE_CERTIFICATES + "(" +
                COL_CERT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_CERT_SHIP_ID + " INTEGER," +
                COL_CERT_NAME + " TEXT," +
                COL_CERT_ISSUE + " TEXT," +
                COL_CERT_EXPIRY + " TEXT)");

        // Reports Table
        db.execSQL("CREATE TABLE " + TABLE_REPORTS + "(" +
                COL_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_REPORT_SHIP_ID + " INTEGER," +
                COL_REPORT_TITLE + " TEXT," +
                COL_REPORT_DATE + " TEXT," +
                COL_REPORT_CONTENT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHIPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CERTIFICATES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);

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

    public boolean insertCrew(int shipId,
                              String name,
                              String rank,
                              String nationality,
                              String passport) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_SHIP_ID, shipId);
        values.put(COL_CREW_NAME, name);
        values.put(COL_RANK, rank);
        values.put(COL_NATIONALITY, nationality);
        values.put(COL_PASSPORT, passport);

        long result = db.insert(TABLE_CREW, null, values);

        return result != -1;
    }
    public Cursor getAllCrew(int shipId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_CREW +
                        " WHERE " + COL_SHIP_ID + "=?",
                new String[]{String.valueOf(shipId)});
    }

    // ===================== EQUIPMENT METHODS =====================

    public boolean insertEquipment(int shipId, String name, String type, String serial) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EQ_SHIP_ID, shipId);
        values.put(COL_EQ_NAME, name);
        values.put(COL_EQ_TYPE, type);
        values.put(COL_EQ_SERIAL, serial);
        long result = db.insert(TABLE_EQUIPMENT, null, values);
        return result != -1;
    }

    public Cursor getAllEquipment(int shipId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_EQUIPMENT + " WHERE " + COL_EQ_SHIP_ID + "=?",
                new String[]{String.valueOf(shipId)});
    }

    // ===================== CERTIFICATES METHODS =====================

    public boolean insertCertificate(int shipId, String name, String issueDate, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CERT_SHIP_ID, shipId);
        values.put(COL_CERT_NAME, name);
        values.put(COL_CERT_ISSUE, issueDate);
        values.put(COL_CERT_EXPIRY, expiryDate);
        long result = db.insert(TABLE_CERTIFICATES, null, values);
        return result != -1;
    }

    public Cursor getAllCertificates(int shipId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CERTIFICATES + " WHERE " + COL_CERT_SHIP_ID + "=?",
                new String[]{String.valueOf(shipId)});
    }

    // ===================== REPORTS METHODS =====================

    public boolean insertReport(int shipId, String title, String date, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_REPORT_SHIP_ID, shipId);
        values.put(COL_REPORT_TITLE, title);
        values.put(COL_REPORT_DATE, date);
        values.put(COL_REPORT_CONTENT, content);
        long result = db.insert(TABLE_REPORTS, null, values);
        return result != -1;
    }

    public Cursor getAllReports(int shipId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_REPORTS + " WHERE " + COL_REPORT_SHIP_ID + "=?",
                new String[]{String.valueOf(shipId)});
    }
}
