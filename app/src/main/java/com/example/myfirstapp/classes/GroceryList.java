package com.example.myfirstapp.classes;
import com.example.myfirstapp.mgr.SupermarketManager;

import java.util.ArrayList;
import java.util.Calendar;
/**
 * GroceryList.java - a simple class that manages
 * the creation of a new list and editing of the list
 * @author Lander
 * @see Supermarket
 */

public class GroceryList {

    private int GL_ID;
    private String Name;
    private float TotalCost;
    private Calendar creationDate;
    /**
     * Stores the productID and quantity of products in a list
     */
    private int[][] ArrayProduct; // Stores ProductID & Quantity

    /**
     * Assigns an ID, list name, creation date to that grocery list
     * set total cost of empty grocery list to 0
     * maximum amount of grocery a list can contain is 50
     * @param listName - name of grocery list
     * @param GL_ID - ID of grocery list
     */

    public GroceryList(String listName, int GL_ID) {
        this.GL_ID = GL_ID;
        this.Name = listName;
        this.TotalCost = 0;
        this.creationDate = Calendar.getInstance();
        this.ArrayProduct = new int[50][2];
    }

    /**
     * read the name keyed in
     * @return name of that particular grocery list
     */
    public String getName() {
        return this.Name;
    }

    /**
     * assign name retrieved to the grocery list
     * @param Name
     */
    public void setName(String Name) {

        this.Name = Name;
    }

    /**
     * @return total amount that the user spent on that grocery list
     */
    public float getTotalCost() {
        return this.TotalCost;

    }

    /**
     * set total cost of a specific grocery list
     * @param smManager
     */
    public void setTotalCost(SupermarketManager smManager) {
        //Iterate through all Product IDs in GroceryList
/**
 * iterate through all product IDs in GroceryList
 * retrieve unit price from productID
 * Multiply unit price by quantity of that product
 * to get the TotalCost of the grocery list
 */
        for (int i = 0; i < ArrayProduct.length; i++) {
            if (ArrayProduct[i][1] != 0) {
                int prod_ID = ArrayProduct[i][0];
                Product[] allProductsInSM = smManager.findSMProductList();
                //Get UnitPrice from ProductID and add (Price*Qty) to TotalCost of GroceryList
                for (int j = 0; j < allProductsInSM.length; j++) {
                    if (prod_ID == allProductsInSM[j].getProductID()) {
                        this.TotalCost += (allProductsInSM[j].getUnitPrice() * ArrayProduct[i][1]);
                    }
                }
            }
        }
    }

    public Calendar getDate() {
        return this.creationDate;
    }

    /**
     * @param creationDate
     */
    public void setDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * get grocery list ID
     * @return grocery list ID
     */
    public int getGL_ID() {
        return this.GL_ID;
    }

    /**
     * @param GL_ID
     */
    public void setGL_ID(int GL_ID) {
        this.GL_ID = GL_ID;
    }

    /**
     * @return a particular product from array list
     */

    public int[][] getArrayProduct() {
        return this.ArrayProduct;
    }

    /**
     * @param ArrayProduct
     */

    public void setArrayProduct(int[][] ArrayProduct) {
        this.ArrayProduct = ArrayProduct;
    }

    /**
     * @param prod_ID
     * @param QTY
     */
    public void addProdToList(int prod_ID, int QTY) {
        for (int i = 0; i < ArrayProduct.length; i++) {
            if (ArrayProduct[i][1] == 0) {
                ArrayProduct[i][0] = prod_ID;
                ArrayProduct[i][1] = QTY;
                break;
            }
            i++;
        }
    }

    /**
     * @param prod_ID
     */
    public void increaseQty(int prod_ID) {
        for (int i = 0; i < ArrayProduct.length; i++) {
            if (ArrayProduct[i][0] == prod_ID) {
                ArrayProduct[i][1] += 1;
                break;
            }
        }
    }

    /**
     * @param prod_ID
     */
    public void decreaseQty(int prod_ID) {
        for (int i = 0; i < ArrayProduct.length; i++) {
            if (ArrayProduct[i][0] == prod_ID) {
                ArrayProduct[i][1] -= 1;
                break;
            }
        }
    }

    /**
     * @param prod_ID
     */
    public void removeProduct(int prod_ID) {
        for (int i = 0; i < ArrayProduct.length; i++){
            if (prod_ID == ArrayProduct[i][0]){
                ArrayProduct[i][1] = 0;
            }
        }
    }

    /**
     * @param prod_ID
     * @return result on whether product is inside the list of healthier choice data
     */
    public boolean checkProdinList(int prod_ID){
        for (int i = 0; i < ArrayProduct.length; i++){
            if (ArrayProduct[i][0] == prod_ID && ArrayProduct[i][1] != 0) {
                return true;
            }
        }
        return false;
    }
}

