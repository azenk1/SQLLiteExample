package com.example.owner.sqlliteexample;

/**
 * TestDataBaseActivity - Activity designed to test the various functions of the SQLite database
 * created.
 */

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class TestDatabaseActivity extends ListActivity {
    private CommentsDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Here, a new DAO is created to access data from other classes.
        datasource = new CommentsDataSource(this);

        //The DAO is opened with this statement.
        datasource.open();

        //List used to store all of the comments retrieved by the getAllComments method of the DAO.
        List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;

        //Switch statement to respond to specific button presses using their resource id values.
        switch (view.getId()) {

            //If the resource id is add them a comment is chosen by Random.nextInt.
            case R.id.add:

                //Randomly selects strings from array comments
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                comment = datasource.createComment(comments[nextInt]);
                adapter.add(comment);
                break;

            //Deletes commnets if the user has pressed the delete button.
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }


    /**
     * Opens the DAO again if the application has resumed.
     */
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    /**
     * Closes the DAO if the application has paused.
     */
    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}