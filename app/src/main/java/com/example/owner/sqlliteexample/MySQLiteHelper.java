package com.example.owner.sqlliteexample;

/**
 * Class: MySQLiteHelper is a subclass of SQLiteOpenHelper. The focus of this class is to create
 * and upgrade a database to be used in this application.
 * Edited By: Al Zenk
 * 04/03/2017
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends  SQLiteOpenHelper{


    //Constant that serves as descriptive name for the table that will be supplied to the superclass.
    public static final String TABLE_COMMENTS = "comments";

    //Constant used to identify each column in the created table.
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";
    public static final String DATABASE_NAME = "comments.db";

    //Integer used to represent the version of the database schema being used.
    public static final int DATABASE_VERSION = 1;

    //Constant for column rating
    public static final String COLUMN_RATING = "rating";

    /**Example of standard SQL create table command
    CREATE TABLE table_name(
            column1 datatype,
            column2 datatype,
            column3 datatype,

            PRIMARY KEY( one or more columns )
);
     **/

    //Database creation statement
    public static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " +
            COLUMN_COMMENT + " text not null);" + COLUMN_RATING;


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION + 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}




