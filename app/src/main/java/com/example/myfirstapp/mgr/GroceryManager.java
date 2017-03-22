package com.example.myfirstapp.mgr;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;

import com.example.myfirstapp.classes.Supermarket;
import com.example.myfirstapp.GroceryUI;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by XY on 8/3/2017.
 */

public class GroceryManager {

    private int currentListID;

    private int latestListID;
    private GroceryUI linkedActivity;
    private ArrayList<GroceryList> gListArray;
    //private GroceryList[] gListArray;

    public GroceryManager(GroceryUI linkedActivity){
        this.linkedActivity = linkedActivity;
    }

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
     * @param listName - name of new list
     */
    public void createNewList(String listName) {
        //Check if list with name == listName exists
        for(int i = 0; i < gListArray.size(); i++){
            if(gListArray.get(i).getName() == listName){
                //Invalid name, name already in use!
                return;
            }
        }

        int newId = latestListID + 1;
        GroceryList newGroceryList = new GroceryList(listName, newId);
        this.addGroceryList(newGroceryList);
        this.gListArray.add(newGroceryList);
        this.currentListID = newId;
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

//    private String[] interpretGroceryLists(GroceryList[] groceryLists){
//        String[] strings = new String[groceryLists.length];
//        for(int i = 0; i < groceryLists.length; i++){
//
//        }
//        return strings;
//    }

    public void loadGroceryUI(){
        //this.getGroceryLists(new Date(), new Date());
        linkedActivity.displayGroceryLists(gListArray.toArray(new GroceryList[gListArray.size()]));
    }

//    public void updateGroceryUI(){
//
//    }

}
