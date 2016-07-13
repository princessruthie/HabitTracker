package com.ruthiefloats.habittracker;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ruthiefloats.habittracker.utilities.DBOpenHelper;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = "HabitTracker MainActy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Code to spin up the database
         */
        DBOpenHelper DBHelper = new DBOpenHelper(this);
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        Log.i(LOGTAG, "Database now created.");


        /*
        Code to check that insert, update and delete methods work
        the read method Logs the number of rows.
         */
        long rowId_1 = DBHelper.insertDogWalk(25, "Cool and pleasant.  Got a pokemon");
        DBHelper.insertDogWalk(5, "Rainy so we came back quickly");
        DBHelper.insertDogWalk(52, "Ran into the guy with the goldendoodle.  Chatted.");
        DBHelper.read();

        DBHelper.updateDogDuration(rowId_1, 30);
        DBHelper.read();

        DBHelper.deleteAllEntries();
        DBHelper.read();

        /*
        Clean up
         */
        db.close();
        Log.i(LOGTAG, "Database now closed");

        /* I used android device monitor to verify:
        data --> data --> com.ruthiefloats.habittracker --> database (empty)
        Also, remember to comment out if you want to test the onUpgrade() code
         */
        DBHelper.deleteDatabase(this);
        Log.i(LOGTAG, "Database has been deleted");
    }
}