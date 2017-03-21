package com.example.myfirstapp.mgr;

import com.example.myfirstapp.classes.GroceryList;

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

    public GroceryList[] getgListArray () {
        return this.gListArray;
    }

    /**
     *
     * @param gListArray
     */
    public void setgListArray(GroceryList[] gListArray) {
        this.gListArray = gListArray;
    }

    /**
     *
     * @param tobeAdded
     */
    public void addGroceryList(GroceryList tobeAdded) {
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
