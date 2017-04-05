package com.example.owner.sqlliteexample;

/**
 * Created by Al Zenk on 4/3/2017.
 * CommentsDataSource - maintains the database connection. Fetches and adds new comments
 */


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    //Specifies context that should be used by class constructor.
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //Closes DAO
    public void close() {
        dbHelper.close();
    }

    /**
     * Creates comment
     * @param comment - String value passed is used to specify comment to be put in.
     * @return - Returns instance of Comment
     */
    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);

        /**
         * table - String that provides the name of the table to query against.
         * columns - list of which columns to return. Here this is specified by insertId
         */
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }


    /**
     * method used to delete a comment
     * @param comment
     */
    public void deleteComment(Comment comment) {

        //id obtained from instance of Comment that was passed.
        long id = comment.getId();

        //Prints out the comment that was deleted.
        System.out.println("Comment deleted with id: " + id);

        //Finds and deletes comment using specified id value.
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /**
     * Returns a list of comments via ArrayList
     * @return
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        /**
         * Table - specifies table to query against.
         * Here, allColumns will be returned.
         */
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}
