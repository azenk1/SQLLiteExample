package com.example.owner.sqlliteexample;

/**
 * Class: Comment - contains data that will be saved in the database and shown to the user via the
 * user interface.
 * Created by Al Zenk on 4/3/2017.
 */

public class Comment {

    //Private data members
    private long id;
    private String comment;


    /**
     * Returns the id value that has been stored in an instance of Comment
      * @return - returns the requested id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id data member. Used by instances of Comment.
     * @param id - sets this.id to value passsed.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the value stored in the comment data member.
     * @return - Returns the requested comment
     */
    public String getComment() {

        return comment;
    }

    /**
     * Sets the comment data member of an instance of Comment
     * @param comment - sets this.comment to the value passed.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}

