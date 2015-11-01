package com.example.arvind.activitytutorial.db;

import android.provider.BaseColumns;
/**
 * Created by arvind on 10/30/15.
 */
public class TaskContract {
    public static final String DB_NAME = "com.example.arvind.activitytutorial.db.tasks";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";

    public class Columns {
        public static final String TASK = "task";
        public static final String _ID = BaseColumns._ID;
    }
}
