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

import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.ui.CreateProfileActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.myfirstapp.ui.MainActivity.thisUsername;

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

    public void editProfile(String username, String password, int healthEmp, String defaultLocation, int dpId){
        ContentValues values = new ContentValues();
        values.put("password", password);
        values.put("dpId", dpId);
        values.put("healthEmphasis", healthEmp);
        values.put("defaultLocation", defaultLocation);
        database.update("Profiles", values,"username" +" = ?", new String[] {username + ""});

    }
    public int getDpId(String username){
        String query = "SELECT dpId FROM Profiles WHERE username = \"" + username + "\";";
        System.out.println(username);
        Cursor cursor = database.rawQuery(query, null);
        if (!cursor.moveToFirst()) {
            cursor.moveToFirst();
        }
        int dpId = cursor.getInt(0);
        cursor.close();
        return dpId;
    }
    public int getHealthEmphasis(String username){
        String query = "SELECT healthEmphasis FROM Profiles WHERE username = \"" + username + "\";";
        Cursor cursor = database.rawQuery(query, null);
        if (!cursor.moveToFirst())
            cursor.moveToFirst();
        int healthEmphasis = cursor.getInt(0);
        cursor.close();
        return healthEmphasis;
    }
    public String getDefaultLocation(String username){
        String query = "SELECT defaultLocation FROM Profiles WHERE username = \"" + username + "\";";
        Cursor cursor = database.rawQuery(query, null);
        if (!cursor.moveToFirst())
            cursor.moveToFirst();
        String defaultLocation = cursor.getString(0);
        cursor.close();
        return defaultLocation;
    }


    public List<String> getSubCategoryList(String category) {
        List<String> list = new ArrayList<>();

        int halal = 0;
        int vegetarian = 0;
        int healthierChoice = 0;
        int glutenFree = 0;
        int dpId = getDpId(thisUsername);

        if ((dpId % 2) == 1){
            glutenFree = 1;
        }
        if (((dpId/2) % 2) == 1){
            healthierChoice = 1;
        }
        if (((dpId/4) % 2) == 1){
            vegetarian = 1;
        }
        if (((dpId/8) % 2) == 1){
            halal = 1;
        }

        Cursor cursor = database.rawQuery("SELECT DISTINCT subCategory FROM ProductList1 " +
                "WHERE category = ? AND halal >= ? AND vegetarian >= ? AND healthierChoice >= ? AND glutenFree >= ? " +
                "ORDER BY " + "subCategory", new String[] {category, String.valueOf(halal),
                String.valueOf(vegetarian), String.valueOf(healthierChoice), String.valueOf(glutenFree)});
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

    /*public void addProduct(String subCategory) {


        String productName;
        int productID;
        String category;
        String brand;
        String subCat;
        float unitPrice;
        int weightOrVolume;
        int item_dpId;
        double healthRating;

        int healthEmphasis;
        int halal = 0;
        int vegetarian = 0;
        int healthierChoice = 0;
        int glutenFree = 0;
        int dpId = getDpId(thisUsername);

        if ((dpId % 2) == 1){
            glutenFree = 1;
        }
        if (((dpId/2) % 2) == 1){
            healthierChoice = 1;
        }
        if (((dpId/4) % 2) == 1){
            vegetarian = 1;
        }
        if (((dpId/8) % 2) == 1){
            halal = 1;
        }

        //Get List of Products in same SubCategory in the Supermarket
       /ArrayList<Product> productsInSubCategory = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM ProductList1 " +
                        "WHERE (subCategory = ? OR productName LIKE '%?%') " +
                "AND halal >= ? AND vegetarian >= ? AND healthierChoice >= ? AND glutenFree >= ? ",
                new String[] {subCategory, subCategory, String.valueOf(halal), String.valueOf(vegetarian),
                        String.valueOf(healthierChoice), String.valueOf(glutenFree)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            productID = cursor.getInt(cursor.getColumnIndex("productID"));
            weightOrVolume = cursor.getInt(cursor.getColumnIndex("weightOrVolume"));
            productName = cursor.getString(cursor.getColumnIndex("productName"));
            category = cursor.getString(cursor.getColumnIndex("category"));
            brand = cursor.getString(cursor.getColumnIndex("brand"));
            unitPrice = cursor.getFloat(cursor.getColumnIndex("unitPrice"));
            healthRating = cursor.getDouble(cursor.getColumnIndex("healthRating"));
            subCat = subCategory;
            item_dpId = cursor.getInt(cursor.getColumnIndex("dpId"));

            Product tobeAdded = new Product(productID, category, brand, productName, subCat,
            unitPrice, item_dpId, weightOrVolume, healthRating);

            productsInSubCategory.add(tobeAdded);

            cursor.moveToNext();
        }
        cursor.close();

        //Get Value of User's healthEmphasis
        Cursor cursor2 = null;
        cursor2 = database.rawQuery("SELECT healthEmphasis FROM Profiles WHERE username = ? ", new String[] {thisUsername + ""});
        if(cursor2.getCount() > 0) {
            cursor2.moveToFirst();
            healthEmphasis = cursor2.getInt(cursor.getColumnIndex("healthEmphasis"));
        }
        cursor2.close();

        //Algorithm to decide which product to add

        ArrayList<Double> recommendationValues = new ArrayList<Double>();
        ArrayList<Integer> recommendationProdID = new ArrayList<Integer>();

        */

        //Add item to grocery list

        /*ContentValues values = new ContentValues();
        values.put("productName", productName);
        values.put("unitPrice", unitPrice);
        values.put("quantity", 1);

        database.insert("List1", null, values);
    }*/

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
        values.put("listUser", thisUsername);
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
        //String query = "SELECT * FROM GLists; ";
        //Cursor cursor = database.rawQuery(query, null);
        Cursor cursor = database.rawQuery("SELECT * FROM GLists WHERE listUser = ? ", new String[] {thisUsername + ""});
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
        if(cursor.getCount() > 0){
            if (!cursor.moveToFirst()){
                cursor.moveToFirst();
            }
            System.out.println(healthierChoiceList.size());

            do{
                String productName = cursor.getString(cursor.getColumnIndex("productName"));
                //Correct until this point
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
            } while(cursor.moveToNext());
        }
    }
    public boolean isLoginValid(String username, String password){
        Cursor cursor = database.rawQuery("SELECT * FROM Profiles WHERE username = ? AND password = ?",new String[] {username,password});
        if(cursor!=null && cursor.getCount()>0) {
                cursor.moveToFirst();
                cursor.close();
                return true;
            }
        else {
            return false;
        }
    }

}

