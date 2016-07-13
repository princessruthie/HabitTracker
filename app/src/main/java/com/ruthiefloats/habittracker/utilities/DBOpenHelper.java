package com.ruthiefloats.habittracker.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created on 7/12/16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {


    private static final String LOGTAG = "HabitTracker OpenHelper";
    private SQLiteDatabase mSQLiteDatabase;

    public DBOpenHelper(Context context) {
        super(context, HabitContract.DATABASE_NAME, null, HabitContract.DATABASE_VERSION);
        mSQLiteDatabase = getWritableDatabase();
        Log.i(LOGTAG, "Helper constructed");
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        mSQLiteDatabase = db;
        db.execSQL(HabitContract.DogWalking.DOG_TABLE_CREATE);
        db.execSQL(HabitContract.TenThousandSteps.STEP_TABLE_CREATE);
        Log.i(LOGTAG, "Tables have been created");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAllTables(db);
        onCreate(db);
        Log.i(LOGTAG, "Database version has been upgraded from " + oldVersion + " to " + newVersion);
    }

    /*
    This requirement was a little unclear.  The only delete
    method I could find needs a Context param
    So I've made this as well as a
    dropAllTables method that simply drops all
    the tables (which was handy anyway)
     */
    public void deleteDatabase(Context context) {
        context.deleteDatabase(HabitContract.DATABASE_NAME);
    }

    /**
     * @param db the db whose tables to drop
     */
    public void dropAllTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + HabitContract.DogWalking.TABLE_DOGWALKING);
        db.execSQL("DROP TABLE IF EXISTS " + HabitContract.TenThousandSteps.TABLE_STEPS);
    }

    /**
     * Deletes all the entries while leaving the Tables
     */
    public void deleteAllEntries() {
        String deleteDogwalking = "DELETE FROM " + HabitContract.DogWalking.TABLE_DOGWALKING;
        String deleteTenThousandSteps = "DELETE FROM " + HabitContract.TenThousandSteps.TABLE_STEPS;
        mSQLiteDatabase.execSQL(deleteDogwalking);
        mSQLiteDatabase.execSQL(deleteTenThousandSteps);
    }

    /**
     * @param duration    How long did you walk the dog
     * @param description Any notes you want to make about the walk
     * @return the generated id, in case you want to save it
     * in an instance variable of a POJO.  I use it to check the update &
     * delete methods
     */
    public long insertDogWalk(int duration, String description) {
        ContentValues values = new ContentValues();
        values.put(HabitContract.DogWalking.COLUMN_DURATION, duration);
        values.put(HabitContract.DogWalking.COLUMN_DESC, description);

        long generatedId = mSQLiteDatabase.insert(HabitContract.DogWalking.TABLE_DOGWALKING, null, values);
        Log.i(LOGTAG, "Successful insert of DogWalk");

        return generatedId;
    }

    /**
     * @return a cursor to all the columns of the DogWalking table
     */
    public Cursor read() {
        Cursor cursor = mSQLiteDatabase.query(HabitContract.DogWalking.TABLE_DOGWALKING,
                HabitContract.DogWalking.allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "Number of rows returned: " + cursor.getCount());

        return cursor;
    }

    /**
     * @param id       ID for the row you want to update
     * @param duration New duration
     */
    public void updateDogDuration(long id, int duration) {
        String query = "UPDATE " +
                HabitContract.DogWalking.TABLE_DOGWALKING +
                " Set " +
                HabitContract.DogWalking.COLUMN_DURATION +
                " = " +
                duration +
                " WHERE " +
                HabitContract.DogWalking.COLUMN_ID +
                " = " +
                id;
        Log.i(LOGTAG, "Duration for row id " + id + " set to " + duration);
    }
}