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
     * @return the instance of Database Access
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


    public List<String> getProductList(String category) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT productName FROM ProductList1 WHERE category = ? ", new String[] {category + ""});
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

    public void createProfile(String username, String password, int healthEmp, String defaultLocation, int dpId){
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("dpId", dpId);
        values.put("healthEmphasis", healthEmp);
        values.put("defaultLocation", defaultLocation);

        database.insert("Profiles", null, values);
    }


    public void updateHCValues(String productName){
        Cursor cursor = database.rawQuery("SELECT  FROM Contact", null);
    }

    public void updateProductHealthierChoice(ArrayList<String> healthierChoiceList){
        Cursor cursor = database.rawQuery("SELECT * FROM ProductList1",null);
        System.out.println("test");
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String productName = cursor.getString(cursor.getColumnIndex("productName"));
                if(healthierChoiceList.contains(productName)){
                    //Add method to set healthier choice of rowIndex i to 1
                    ContentValues values = new ContentValues();
                    values.put("healthierChoice", 1);
                    database.update("ProductList1", values, "productName = ?", new String[]{productName});
                }   else {
                    //Add method to set healthier choice of rowIndex i to 0
                    ContentValues values = new ContentValues();
                    values.put("healthierChoice", 0);
                    database.update("ProductList1", values, "productName = ?", new String[]{productName});
                }
            }
        }
    }


    public int getDpId(String username){
        String query = "SELECT dpId FROM Profiles WHERE username = \"" + username + "\";";
        Cursor cursor = database.rawQuery(query, null);
        if (!cursor.moveToFirst())
            cursor.moveToFirst();
        int dpId = cursor.getInt(0);
        cursor.close();
        return dpId;
    }

}
