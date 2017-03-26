package com.example.myfirstapp.mgr;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;

import com.example.myfirstapp.classes.Profile;
import com.example.myfirstapp.classes.Supermarket;
import com.example.myfirstapp.GroceryUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


/**
 * Created by XY on 8/3/2017.
 */

public class GroceryManager {

    private int currentListID;
    private int latestListID;
    private GroceryUI linkedActivity;
    private ArrayList<GroceryList> gListArray;

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
            if(listName.equals(gListArray.get(i).getName())){
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
        gListArray.add(tobeAdded);
    }

    public void addProduct(String productToAdd, SupermarketManager smManager, ProfileManager profileManager){

        double recValue;
        int recProdID;
        int userHealthEmp = 3;


        //Get List of Products with Same Type/Name available in Current Supermarket
        Product[] availProd = smManager.findSMProductList();
        ArrayList<Product> sameProduct = new ArrayList<Product>();

        for (int i = 0; i < availProd.length; i++){
            if (productToAdd.equals(availProd[i].getSubCategory()) || availProd[i].getProductName().contains(productToAdd))
                sameProduct.add(availProd[i]);
        }

        //Get Value of User's Health Emphasis in his/her profile
        for (int k = 0; k < profileManager.getProfileArray().size(); k++){
            if (profileManager.getCurProfileID() == profileManager.getProfileArray().get(k).getProfileID()){
                userHealthEmp = profileManager.getProfileArray().get(k).getHealthEmphasis();
                break;
            }
        }
        //Algorithm to decide which exact item to recommend/add
        ArrayList<Double> recommendationValues = new ArrayList<Double>();
        ArrayList<Integer> recommendationProdID = new ArrayList<Integer>();

        for (int j = 0; j < sameProduct.size(); j++){
            if(userHealthEmp == 1) {
                recValue = 0.8 * sameProduct.get(j).getHealthRating() + (1.0 / sameProduct.get(j).getUnitPrice()) * 1.2;

            }

            else if(userHealthEmp == 2) {
                recValue = 0.9 * sameProduct.get(j).getHealthRating() + (1.0 / sameProduct.get(j).getUnitPrice()) * 1.1;
            }

            else if(userHealthEmp == 3) {
                recValue = 1.0 * sameProduct.get(j).getHealthRating() + (1.0 / sameProduct.get(j).getUnitPrice()) * 1.0;
            }

            else if(userHealthEmp == 4) {
                recValue = 1.1 * sameProduct.get(j).getHealthRating() + (1.0 / sameProduct.get(j).getUnitPrice()) * 0.9;
            }

            else {
                recValue = 1.2 * sameProduct.get(j).getHealthRating() + (1.0 / sameProduct.get(j).getUnitPrice()) * 0.8;
            }

            recommendationValues.add(recValue);
            recProdID = sameProduct.get(j).getProductID();
            recommendationProdID.add(recProdID);
        }

        double maxRecValue = Collections.max(recommendationValues);
        int index = recommendationValues.indexOf(maxRecValue);
        recProdID = recommendationProdID.get(index);

        //Add Item to List
        addSpecificItem(recProdID, 1);
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

    public void confirmList (int GL_ID) {
        //Add List to ListHistory/Expenditure. Check who is doing and how to implement
        for (int i = 0; i < gListArray.size(); i++){
            if (GL_ID == gListArray.get(i).getGL_ID()){
                gListArray.remove(i);
            }
        }
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
