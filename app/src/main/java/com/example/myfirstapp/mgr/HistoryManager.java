package com.example.myfirstapp.mgr;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * HistoryManager.java
 * @author Daniel
 */

public class HistoryManager {

    private DatabaseAccess databaseAccess;
    private ArrayList<GroceryList> gListArray;
    private Context appContext;

    /**
     *
     * @param context
     */
    public HistoryManager(Context context){
        appContext = context;
        databaseAccess = DatabaseAccess.getInstance(context);
        gListArray = new ArrayList<GroceryList>(0);
        loadGroceryLists();
    }


    public void loadGroceryLists(){
        gListArray.clear();
        databaseAccess.open();
        Cursor cursor = databaseAccess.pullHLists();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                GroceryList gList;

                // Read columns data
                int id = (int) cursor.getLong(cursor.getColumnIndex("_id"));
                String listName = cursor.getString(cursor.getColumnIndex("Name"));
                float totalCost = (float) cursor.getDouble(cursor.getColumnIndex("TotalCost"));
                Date listDate;

                // Make Grocery List
                gList = new GroceryList(listName, id);
                gList.setTotalCost(totalCost);
                try {
                    listDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cursor.getString(cursor.getColumnIndex("creationDate")));
                    gList.setDate(listDate);
                } catch (Exception e){
                    Toast.makeText(appContext,"Could not parse date!, using instance time",Toast.LENGTH_LONG).show();
                }

                Cursor productCursor = databaseAccess.pullProductsOfList(listName);
                if (productCursor.getCount() > 0) {
                    int index = 0;
                    while (productCursor.moveToNext()) {
                        int productId = productCursor.getInt(productCursor.getColumnIndex("productID"));
                        int quantity = productCursor.getInt(productCursor.getColumnIndex("quantity"));
                        Product product = databaseAccess.getProductById(productId);

                        gList.addProdToList(product,quantity);
                    }
                }

                gListArray.add(gList);
            }
        }
        databaseAccess.close();
    }

    public GroceryList getGroceryList(int list_id){
        for(int i = 0; i < gListArray.size(); i++){
            if(gListArray.get(i).getGL_ID() == list_id){
                return gListArray.get(i);
            }
        }
        return null;
    }

    public ArrayList<GroceryList> getgListArray(){
        return this.gListArray;
    }
}
