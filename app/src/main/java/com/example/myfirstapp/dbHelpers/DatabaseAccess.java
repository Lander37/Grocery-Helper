package com.example.myfirstapp.dbHelpers;

/**
 * DatabaseAccess.java- to read and write the supermarket database into this application
 * so that it would constantly be up to date.
 * @author Lander
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }


    public List<String> getProductList() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT productName FROM ProductList1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public double getUnitPrice(String productName) {
        Cursor cursor = null;
        double unitPrice = 0;
        try {
            cursor = database.rawQuery("SELECT unitPrice FROM ProductList1 WHERE productName = ? ", new String[] {productName + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                unitPrice = cursor.getDouble(cursor.getColumnIndex("unitPrice"));
            }
            return unitPrice;
        }finally {
            cursor.close();
        }
    }

    public void addProduct(String productName, double unitPrice) {
        ContentValues values = new ContentValues();
        values.put("productName", productName);
        values.put("unitPrice", unitPrice);
        values.put("quantity", 1);

        database.insert("List1", null, values);
    }

    public int createGList(String listName) {

        database.execSQL("UPDATE GLists SET isCurrent = 0");

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNow = sdf.format(cal.getTime());

        ContentValues values = new ContentValues();
        values.put("Name", listName);
        values.put("TotalCost", 0);
        values.put("isHistory", 0);
        values.put("isCurrent", 1);
        values.put("creationDate", dateNow);


        long idLong = database.insert("GLists", null, values);
        int id = (int)idLong;


        String query = "CREATE TABLE " + listName + "(productName TEXT NOT NULL PRIMARY KEY, " +
                "quantity INTEGER NOT NULL, unitPrice REAL NOT NULL);";
        database.execSQL(query);

        return id;
    }

    public boolean listNameValidity(String listName) {
        String query = "SELECT Name FROM GLists WHERE Name = \"" + listName + "\";";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public Cursor populateGListTable() {
        String query = "SELECT Name, TotalCost FROM GLists; ";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    public Cursor pullGLists(){
        String query = "SELECT * FROM GLists; ";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
}
