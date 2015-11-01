package com.example.arvind.activitytutorial;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.arvind.activitytutorial.db.TaskContract;
import com.example.arvind.activitytutorial.db.TaskDBHelper;

public class MainActivity extends AppCompatActivity {

    private TaskDBHelper helper;
    private ListAdapter listAdapter;

    private void updateUI() {
        helper = new TaskDBHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TABLE, new String[]{TaskContract.Columns.TASK}, null, null, null, null, null);

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.task_view,
                cursor,
                new String[]{TaskContract.Columns.TASK},
                new int[]{R.id.taskTextView},
                0
        );
        this.setListAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        SQLiteDatabase sqlDB = new TaskDBHelper(this).getWritableDatabase();
//        Cursor cursor = sqlDB.query(TaskContract.TABLE, new String[]{TaskContract.Columns.TASK}, null,null,null,null,null);
//        cursor.moveToFirst();
//
//        while(cursor.moveToNext()){
//            Log.d("MainActivity cursor",
//                    cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.Columns.TASK)));
//        }
        updateUI();

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                AlertDialog.Builder builder  = new AlertDialog.Builder(this);
                builder.setTitle("Add a task");
                builder.setMessage("What would you like to do?");
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                   @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                       String task = inputField.getText().toString();
                       Log.d("MainActivity", task);
                       helper = new TaskDBHelper(MainActivity.this);
                       SQLiteDatabase db = helper.getWritableDatabase();
                       ContentValues content = new ContentValues();

                       content.clear();
                       content.put(TaskContract.Columns.TASK, task);

                       db.insertWithOnConflict(TaskContract.TABLE, null, content, SQLiteDatabase.CONFLICT_IGNORE);


                   }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
                return true;
            default:
                return false;
        }
    }
}
