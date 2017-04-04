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
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.mgr.GroceryManager;
import com.example.myfirstapp.ui.CreateProfileActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    public void setIsHistory(int gl_id){
        ContentValues values = new ContentValues();
        values.put("isHistory",1);
        database.update("GLists",values,"_id="+gl_id,null);

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
        if ((dpId % 2) == 1){ glutenFree = 1;}
        if (((dpId/2) % 2) == 1){healthierChoice = 1;}
        if (((dpId/4) % 2) == 1){vegetarian = 1;}
        if (((dpId/8) % 2) == 1){halal = 1;}
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

    public List<String> getHLists(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Name + TotalCost FROM GLists WHERE listUser = ? AND isHistory = ?", new String[] {thisUsername + "", "1"});
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

    public ArrayList<Product> listProductsInSubCategory(String subCategory){

        String productName;
        int productID;
        String category;
        String brand;
        String subCat;
        float unitPrice;
        int weightOrVolume;
        int item_dpId;
        double healthRating;

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
        String compare = "%" + subCategory + "%";

        Cursor cursor = database.rawQuery("SELECT * FROM ProductList1 " +
                        "WHERE (subCategory = ? OR productName LIKE ? ) " +
                        "AND halal >= ? AND vegetarian >= ? AND healthierChoice >= ? AND glutenFree >= ? ",
                new String[] {subCategory, compare, String.valueOf(halal), String.valueOf(vegetarian),
                        String.valueOf(healthierChoice), String.valueOf(glutenFree)});

        //Add Products to ArrayList
        ArrayList<Product> productsInSubCategory = new ArrayList<>();

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
        return productsInSubCategory;
    }

    public int getUserHealthEmp(){

        int userHealthEmp = 0;

        //Get Value of User's healthEmphasis
        Cursor cursor2 = null;
        cursor2 = database.rawQuery("SELECT healthEmphasis FROM Profiles WHERE username = ? ", new String[] {thisUsername + ""});
        if(cursor2.getCount() > 0) {
            cursor2.moveToFirst();
            userHealthEmp = cursor2.getInt(cursor2.getColumnIndex("healthEmphasis"));
        }
        cursor2.close();
        return userHealthEmp;
    }

    public int chooseSpecificProductID(ArrayList<Product> productsForCompare, int userHealthEmp){

        double recValue;
        int recProdID;

        //Algorithm to decide which product to add
        ArrayList<Double> recommendationValues = new ArrayList<Double>();
        ArrayList<Integer> recommendationProdID = new ArrayList<Integer>();

        for (int j = 0; j < productsForCompare.size(); j++) {
            if (userHealthEmp == 1) {
                recValue = 0.8 * productsForCompare.get(j).getHealthRating() +
                        1.2 * (productsForCompare.get(j).getWeightOrVolume() / productsForCompare.get(j).getUnitPrice() * 100);
            } else if (userHealthEmp == 2) {
                recValue = 0.9 * productsForCompare.get(j).getHealthRating() +
                        1.1 * (productsForCompare.get(j).getWeightOrVolume() / productsForCompare.get(j).getUnitPrice() * 100);
            } else if (userHealthEmp == 3) {
                recValue = 1.0 * productsForCompare.get(j).getHealthRating() +
                        1.0 * (productsForCompare.get(j).getWeightOrVolume() / productsForCompare.get(j).getUnitPrice() * 100);
            } else if (userHealthEmp == 4) {
                recValue = 1.1 * productsForCompare.get(j).getHealthRating() +
                        0.9 * (productsForCompare.get(j).getWeightOrVolume() / productsForCompare.get(j).getUnitPrice() * 100);
            } else {
                recValue = 1.2 * productsForCompare.get(j).getHealthRating() +
                        0.8 * (productsForCompare.get(j).getWeightOrVolume() / productsForCompare.get(j).getUnitPrice() * 100);
            }

            recommendationValues.add(recValue);
            recProdID = productsForCompare.get(j).getProductID();
            recommendationProdID.add(recProdID);
        }

        double maxRecValue = Collections.max(recommendationValues);
        int index = recommendationValues.indexOf(maxRecValue);
        recProdID = recommendationProdID.get(index);
        return recProdID;
    }

    public void addProduct(String subCategory, int gl_id) {

        float unitPrice = 0;
        String productName = "";
        String listName = "";
        String brand = "";

        ArrayList<Product> productsForCompare = listProductsInSubCategory(subCategory);
        int userHealthEmp = getUserHealthEmp();
        int recProdID = chooseSpecificProductID(productsForCompare, userHealthEmp);

        //Add item to grocery list
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT productName, unitPrice, brand FROM ProductList1 WHERE productID = ? ", new String[]{recProdID + ""});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            productName = productName.concat(cursor.getString(cursor.getColumnIndex("productName")));
            unitPrice = cursor.getFloat(cursor.getColumnIndex("unitPrice"));
            brand = brand.concat(cursor.getString(cursor.getColumnIndex("brand")));
        }
        cursor.close();

        ContentValues values = new ContentValues();
        values.put("productName", productName);
        values.put("unitPrice", unitPrice);
        values.put("brand", brand);
        values.put("quantity", 1);

        Cursor cr = null;
        cr = database.rawQuery("SELECT Name FROM GLists WHERE _id = ? ", new String[]{gl_id + ""});
        if (cr.getCount() > 0) {
            cr.moveToFirst();
            listName = listName.concat(cr.getString(cr.getColumnIndex("Name")));
            database.insert(listName, null, values);
        }
        refreshListCosts(gl_id, listName);
    }

    public void refreshListCosts(int gl_id, String listName){

        int totalCost = 0;
        int quantity = 0;
        float unitPrice = 0;
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT quantity, unitPrice FROM " + listName, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                unitPrice = cursor.getFloat(cursor.getColumnIndex("unitPrice"));
                totalCost += (quantity*unitPrice);
                cursor.moveToNext();
            }
        }
        database.execSQL("UPDATE GLists SET TotalCost = " + totalCost + "" + " WHERE _id = " + gl_id + "");
        cursor.close();

    }

    public int createGList(String listName) {
        try {
            database.execSQL("UPDATE GLists SET isCurrent = 0 WHERE listUser = " + thisUsername + "");
        } catch (SQLiteException e){
        }
        finally {
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
            int id = (int) idLong;


            String query = "CREATE TABLE " + listName + "(productName TEXT NOT NULL PRIMARY KEY, " +
                    "quantity INTEGER NOT NULL, unitPrice REAL NOT NULL, brand TEXT NOT NULL);";
            database.execSQL(query);

            return id;
        }
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
        Cursor cursor = database.rawQuery("SELECT * FROM GLists WHERE listUser = ? AND isHistory = ?", new String[] {thisUsername + "", "0"});
        return cursor;
    }

    public Cursor pullHLists(){
        //String query = "SELECT * FROM GLists; ";
        //Cursor cursor = database.rawQuery(query, null);
        Cursor cursor = database.rawQuery("SELECT * FROM GLists WHERE listUser = ? AND isHistory = ?", new String[] {thisUsername + "", "1"});
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
    public int getMonthlyExpenditure(String month){
        String compare = month + "%";
        //Cursor cursor = database.rawQuery("SELECT SUM(TotalCost) FROM GLists instr(creationDate, ?)>0 AND isHistory = ?",new String[] {month,"1"});
        Cursor cursor = database.rawQuery("SELECT SUM(TotalCost) FROM GLists WHERE  isHistory = ? AND creationDate LIKE ?",new String[] {"1",compare});
        if (!cursor.moveToFirst()) {

            cursor.moveToFirst();
        }
        int monthlyExpenditure = cursor.getInt(0);
        cursor.close();
        System.out.println(monthlyExpenditure);
        return monthlyExpenditure;


    public void deleteProductFromList (int gl_id, String subCategory){

        String listName = "";

        Cursor cursor = null;
        cursor = database.rawQuery("SELECT Name FROM GLists WHERE _id = ? ", new String[]{gl_id + ""});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            listName = listName.concat(cursor.getString(cursor.getColumnIndex("Name")));
        }
        cursor.close();

        database.delete(listName, "subCategory = ? ", new String[] {subCategory});
    }

}

