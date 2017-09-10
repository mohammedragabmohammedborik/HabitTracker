package com.example.mohammedragab.habittracker.database;

import android.provider.BaseColumns;

/**
 * Created by MohammedRagab on 5/12/2017.
 */

// API for Habit Tracker app.
public class ReadingContract {
    public ReadingContract() {
    }

    // ineer class that define constant data for habit tracker.
    public static final class HabitEntry implements BaseColumns {
        // define table name.
        public final static String TABLE_NAME = "Leisure";
        // unique id  for habit tracker .
        public final static String _ID = BaseColumns._ID;
        // name of book that read during leisure.
        public final static String COLUMN_BOOK = "book";
        //name of drinking during leisure.
        public final static String COLUMN_DRINK = "drink";
        // number of hour during lesiure.
        public final static String COLUMN_HOURS = "hours";


    }
}
