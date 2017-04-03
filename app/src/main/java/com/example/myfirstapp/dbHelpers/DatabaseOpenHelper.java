package com.example.myfirstapp.dbHelpers;

/**DatabaseOpenHelper.java
 * Created by Lander on 02-Apr-17.
 */
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "TestDB.db";
    private static final int DATABASE_VERSION = 1;

    /**
     *
     * @param context
     */

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}