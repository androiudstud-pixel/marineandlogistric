package com.example.marineandlogistric;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MarineDB.db";
    private static final int DATABASE_VERSION = 5;

    // Table: Users
    private static final String TABLE_USERS = "users";
    private static final String COL_USER_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";

    // Table: Ships
    private static final String TABLE_SHIPS = "ships";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_IMO = "imo";
    private static final String COL_TYPE = "type";
    private static final String COL_FLAG = "flag";
    private static final String COL_STATUS = "status";

    // Table: Crew
    private static final String TABLE_CREW = "crew";
    private static final String COL_CREW_ID = "crew_id";
    private static final String COL_CREW_NAME = "crew_name";
    private static final String COL_RANK = "rank";
    private static final String COL_NATIONALITY = "nationality";
    private static final String COL_PASSPORT = "passport";
    private static final String COL_SHIP_ID = "ship_id";

    // Table: Equipment
    private static final String TABLE_EQUIPMENT = "equipment";
    private static final String COL_EQ_ID = "eq_id";
    private static final String COL_EQ_NAME = "eq_name";
    private static final String COL_EQ_TYPE = "eq_type";
    private static final String COL_EQ_SERIAL = "eq_serial";
    private static final String COL_EQ_SHIP_ID = "eq_ship_id";

    // Table: Certificates
    private static final String TABLE_CERTIFICATES = "certificates";
    private static final String COL_CERT_ID = "cert_id";
    private static final String COL_CERT_NAME = "cert_name";
    private static final String COL_CERT_ISSUE = "issue_date";
    private static final String COL_CERT_EXPIRY = "expiry_date";
    private static final String COL_CERT_SHIP_ID = "cert_ship_id";

    // Table: Reports
    private static final String TABLE_REPORTS = "reports";
    private static final String COL_REPORT_ID = "report_id";
    private static final String COL_REPORT_TITLE = "report_title";
    private static final String COL_REPORT_DATE = "report_date";
    private static final String COL_REPORT_CONTENT = "report_content";
    private static final String COL_REPORT_SHIP_ID = "report_ship_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users Table
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT)");

        // Ships Table
        db.execSQL("CREATE TABLE " + TABLE_SHIPS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_IMO + " TEXT, " +
                COL_TYPE + " TEXT, " +
                COL_FLAG + " TEXT, " +
                COL_STATUS + " TEXT)");

        // Crew Table
        db.execSQL("CREATE TABLE " + TABLE_CREW + " (" +
                COL_CREW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_CREW_NAME + " TEXT, " +
                COL_RANK + " TEXT, " +
                COL_NATIONALITY + " TEXT, " +
                COL_PASSPORT + " TEXT, " +
                COL_SHIP_ID + " INTEGER)");

        // Equipment Table
        db.execSQL("CREATE TABLE " + TABLE_EQUIPMENT + " (" +
                COL_EQ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EQ_NAME + " TEXT, " +
                COL_EQ_TYPE + " TEXT, " +
                COL_EQ_SERIAL + " TEXT, " +
                COL_EQ_SHIP_ID + " INTEGER)");

        // Certificates Table
        db.execSQL("CREATE TABLE " + TABLE_CERTIFICATES + " (" +
                COL_CERT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_CERT_NAME + " TEXT, " +
                COL_CERT_ISSUE + " TEXT, " +
                COL_CERT_EXPIRY + " TEXT, " +
                COL_CERT_SHIP_ID + " INTEGER)");

        // Reports Table
        db.execSQL("CREATE TABLE " + TABLE_REPORTS + " (" +
                COL_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_REPORT_TITLE + " TEXT, " +
                COL_REPORT_DATE + " TEXT, " +
                COL_REPORT_CONTENT + " TEXT, " +
                COL_REPORT_SHIP_ID + " INTEGER)");

        // Insert Default Admin
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, "admin");
        values.put(COL_PASSWORD, "1234");
        db.insert(TABLE_USERS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 5) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                    COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_USERNAME + " TEXT UNIQUE, " +
                    COL_PASSWORD + " TEXT)");

            // Add default admin if version was lower
            ContentValues values = new ContentValues();
            values.put(COL_USERNAME, "admin");
            values.put(COL_PASSWORD, "1234");
            db.insertWithOnConflict(TABLE_USERS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }
    }

    // ===================== USER METHODS =====================

    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_USER_ID};
        String selection = COL_USERNAME + " = ?" + " AND " + COL_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // ===================== SHIP METHODS =====================

    public boolean insertShip(String name, String imo, String type, String flag, String status) {
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
        return db.delete(TABLE_SHIPS, COL_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    // ===================== CREW METHODS =====================

    public boolean insertCrew(int shipId, String name, String rank, String nationality, String passport) {
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
        if (shipId == -1) {
            return db.rawQuery("SELECT * FROM " + TABLE_CREW, null);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_CREW + " WHERE " + COL_SHIP_ID + "=?", new String[]{String.valueOf(shipId)});
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
        if (shipId == -1) {
            return db.rawQuery("SELECT * FROM " + TABLE_EQUIPMENT, null);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_EQUIPMENT + " WHERE " + COL_EQ_SHIP_ID + "=?", new String[]{String.valueOf(shipId)});
    }

    // ===================== CERTIFICATE METHODS =====================

    public boolean insertCertificate(int shipId, String name, String issue, String expiry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CERT_SHIP_ID, shipId);
        values.put(COL_CERT_NAME, name);
        values.put(COL_CERT_ISSUE, issue);
        values.put(COL_CERT_EXPIRY, expiry);
        long result = db.insert(TABLE_CERTIFICATES, null, values);
        return result != -1;
    }

    public Cursor getAllCertificates(int shipId) {
        SQLiteDatabase db = this.getReadableDatabase();
        if (shipId == -1) {
            return db.rawQuery("SELECT * FROM " + TABLE_CERTIFICATES, null);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_CERTIFICATES + " WHERE " + COL_CERT_SHIP_ID + "=?", new String[]{String.valueOf(shipId)});
    }

    // ===================== REPORT METHODS =====================

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
        if (shipId == -1) {
            return db.rawQuery("SELECT * FROM " + TABLE_REPORTS, null);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_REPORTS + " WHERE " + COL_REPORT_SHIP_ID + "=?", new String[]{String.valueOf(shipId)});
    }
}
