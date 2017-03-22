package com.example.myfirstapp.mgr;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.mgr.SupermarketManager;

import java.util.ArrayList;

/**
 * Created by XY on 8/3/2017.
 */

public class GroceryManager {

    private int currentListID;
    private ArrayList<GroceryList> gListArray;

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

    public ArrayList<GroceryList> getgListArray () {
        return this.gListArray;
    }

    /**
     *
     * @param gListArray
     */
    public void setgListArray(ArrayList<GroceryList> gListArray) {
        this.gListArray = gListArray;
    }

    /**
     *
     * @param tobeAdded
     */
    public void addGroceryList(GroceryList tobeAdded) {
        int i = 0;
        while (true){
            if (gListArray.get(i) == null){
                gListArray.set(i, tobeAdded);
                break;
            }
            i++;
        }
    }

    public void addProduct(String productType, SupermarketManager smManager){
        Product[] availProd = smManager.findSMProductList();
        ArrayList<Product> sameProductType = new ArrayList<Product>();

        for (int i = 0; i < availProd.length; i++){
            if (productType.equals(availProd[i].getProductType()))
                sameProductType.add(availProd[i]);
        }
        //Out of those products, use algorithm?? to decide which one to add -> Need to Code

        //Get Product ID & Qty and pass into addSpecificItem(int prod_ID, int QTY) -> Need to Code

    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void addSpecificItem(int prod_ID, int QTY) {
        for (int i = 0; i < gListArray.size(); i++){
            if (currentListID == gListArray.get(i).getGL_ID()){
                gListArray.get(i).addProdToList(prod_ID , QTY);
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
