package com.ruthiefloats.habittracker.utilities;

/**
 * Created on 7/12/16.
 */
public class HabitContract {

    public static final String DATABASE_NAME = "habits.db";
    public static final int DATABASE_VERSION = 1;

    static class DogWalking {
        public static final String TABLE_DOGWALKING = "dogwalking";

        public static final String COLUMN_ID = "deedId";
        /*Length of dog walk in minutes*/
        public static final String COLUMN_DURATION = "duration";
        /*How was it?  ex/ Fun but rainy */
        public static final String COLUMN_DESC = "description";

        static final String DOG_TABLE_CREATE =
                "CREATE TABLE " + TABLE_DOGWALKING + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DESC + " TEXT, " +
                        COLUMN_DURATION + " NUMERIC " +
                        ")";
        /*used by the read() method to get a Cursor to all the columns*/
        public static final String[] allColumns = {
                HabitContract.DogWalking.COLUMN_ID,
                DogWalking.COLUMN_DESC,
                DogWalking.COLUMN_DURATION
        };
    }

    static class TenThousandSteps {
        public static final String TABLE_STEPS = "walking";
        public static final String COLUMN_ID = "deedId";
        /*How many steps did you take */
        public static final String COLUMN_NUM_STEPS = "numSteps";
        /*How was it ex/ Difficult because I just ate three burgers. */
        public static final String COLUMN_DESC = "description";
        public static final String STEP_TABLE_CREATE =
                "CREATE TABLE " + TABLE_STEPS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DESC + " TEXT, " +
                        COLUMN_NUM_STEPS + " NUMERIC " +
                        ")";
    }
}