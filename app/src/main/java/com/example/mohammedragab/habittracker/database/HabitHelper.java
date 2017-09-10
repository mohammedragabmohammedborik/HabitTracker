package com.example.mohammedragab.habittracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mohammedragab.habittracker.database.ReadingContract.HabitEntry;

/**
 * Created by MohammedRagab on 5/13/2017.
 */

public class HabitHelper extends SQLiteOpenHelper {
    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habitTracker.db";
    // version of database.
    private static final int DATABASE_VERSION = 1;

    public HabitHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // this method is called when database created
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_LESUIRE_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_BOOK + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_DRINK + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HOURS + " INTEGER NOT NULL DEFAULT 0); ";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_LESUIRE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // no version updated
    }
}
