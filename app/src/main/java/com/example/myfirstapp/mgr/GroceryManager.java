package com.example.myfirstapp.mgr;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.classes.Supermarket;

/**
 * Created by XY on 8/3/2017.
 */

public class GroceryManager {

    private int currentListID;
    private GroceryList[] gListArray;

    public int getcurrentListID() {
            return this.currentListID;
    }

    /**
     *
     * @param list_ID
     */
    public void setcurrentListID(int list_ID) {
        this.currentListID = list_ID;
    }

    /**
     *
     * @param tobeAdded
     */
    public void addGroceryList(GroceryList tobeAdded) {
        // TODO - implement GroceryManager.addGroceryList
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void addProduct(int prod_ID, int QTY) {
        for (int i = 0; i < gListArray.length; i++){
            if (currentListID == gListArray[i].getGL_ID()){
                gListArray[i].addProdToList(prod_ID , QTY);
                break;
            }
        }
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void removeProduct(String prod_ID, int QTY) {
        // TODO - implement GroceryManager.removeProduct
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod_ID
     */
    public int updateQTY(String prod_ID) {
        // TODO - implement GroceryManager.updateQTY
        throw new UnsupportedOperationException();
    }

}
