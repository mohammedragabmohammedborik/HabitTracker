package com.example.mohammedragab.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammedragab.habittracker.database.HabitHelper;
import com.example.mohammedragab.habittracker.database.ReadingContract.HabitEntry;

public class HapitTrackerOfLeisure extends AppCompatActivity {
    private Cursor cursor;
    private HabitHelper habitHelper;
    // edit text for enter book name
    private EditText book;
    // edit text for enter drink;
    private EditText drink;
    // edit text for number of hours;
    private EditText hours;
    // text view view for data display.
    private TextView textView;
    // button for insert data
    private Button insert_button;
    // button for read data.
    private Button read_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        // find id for all view we use
        book = (EditText) findViewById(R.id.book);
        drink = (EditText) findViewById(R.id.drink);
        hours = (EditText) findViewById(R.id.hours);
        textView = (TextView) findViewById(R.id.text_view);
        insert_button = (Button) findViewById(R.id.insert);
        read_button = (Button) findViewById(R.id.read);
        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        read_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read();
            }
        });
        //
        habitHelper = new HabitHelper(this);
    }

    // method for insert data
    public void insert() {
        // get text from Edittext
        String book_name = book.getText().toString().trim();
        String drink_name = drink.getText().toString().trim();
        String hours_number = hours.getText().toString().trim();
        int hourNumber = Integer.parseInt(hours_number);
        // Gets the data repository in write mode
        SQLiteDatabase db = habitHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_BOOK, book_name);
        values.put(HabitEntry.COLUMN_DRINK, drink_name);
        values.put(HabitEntry.COLUMN_HOURS, hourNumber);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, R.string.faildinsert, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.datainsert, Toast.LENGTH_SHORT).show();
        }

    }

    private Cursor setquery() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = habitHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_BOOK,
                HabitEntry.COLUMN_DRINK,
                HabitEntry.COLUMN_HOURS};

        // Perform a query on the pets table
        cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        return cursor;
    }

    // method to read data and  display in text view
    private void read() {
        cursor = setquery();

        try {
            // Create a header in the Text View that looks like this:
            //
            // The habit tracer table contains <number of rows in Cursor> .
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            textView.setText("The habit tracker table contains " + cursor.getCount() + " habit tracker.\n\n");
            textView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_BOOK + " - " +
                    HabitEntry.COLUMN_DRINK + " - " +
                    HabitEntry.COLUMN_HOURS + " - " + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int bookColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_BOOK);
            int drinkColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DRINK);
            int hoursColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HOURS);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentBook = cursor.getString(bookColumnIndex);
                String currentDrink = cursor.getString(drinkColumnIndex);
                int currentHours = cursor.getInt(hoursColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                textView.append(("\n" + currentID + " - " +
                        currentBook + " - " +
                        currentDrink + " - " +
                        currentHours + " - "));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
