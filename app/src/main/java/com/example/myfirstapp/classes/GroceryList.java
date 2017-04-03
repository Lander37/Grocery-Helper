package com.example.myfirstapp.classes;
import com.example.myfirstapp.mgr.SupermarketManager;

import java.util.ArrayList;
import java.util.Calendar;
/**
 * GroceryList.java - a simple class that manages
 * the creation of a new list and editing of the list.
 * @author Lander
 * @see Supermarket
 */

public class GroceryList {

    private int GL_ID;
    private String Name;
    private float TotalCost;
    private Calendar creationDate;
    /**
     * Stores the productID and quantity of products in a list.
     */
    private int[][] ArrayProduct; // Stores ProductID & Quantity

    /**
     * Assigns an ID, list name, creation date to that grocery list.
     * Set total cost of empty grocery list to 0.
     * Maximum amount of grocery a list can contain is 50.
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
     * Read the name that was keyed in by user.
     * @return name of that particular grocery list.
     */
    public String getName() {
        return this.Name;
    }

    /**
     * Assign name retrieved to the grocery list of the ID.
     * @param Name name of that particular grocery list.
     */
    public void setName(String Name) {

        this.Name = Name;
    }

    /**
     * @return total amount that the user spent on that grocery list.
     */
    public float getTotalCost() {
        return this.TotalCost;

    }

    /**
     * Set total cost of a specific grocery list.
     * @param smManager
     * @see Supermarket
     */
    public void setTotalCost(SupermarketManager smManager) {
        //Iterate through all Product IDs in GroceryList
/**
 * Iterate through all product IDs in GroceryList.
 * Retrieve unit price from productID.
 * Multiply unit price by quantity of that product
 * to get the TotalCost of the grocery list.
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
     * Gets the current date from Calender
     * and assign it to the grocery list.
     * @param creationDate date this grocery list is created.
     */
    public void setDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets this grocery list ID.
     * @return ID of this grocery list.
     */
    public int getGL_ID() {
        return this.GL_ID;
    }

    /**
     * Assigns the ID to this grocery list.
     * @param GL_ID ID of this grocery list.
     */
    public void setGL_ID(int GL_ID) {
        this.GL_ID = GL_ID;
    }

    /**
     * @return
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
     * Add Product and quantity of this product to array list
     * @param prod_ID ID of this product
     * @param QTY quantity of this product in grocery list
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
     * Increases the quantity of a product in this list.
     * Transverse through grocery list.
     * Once a match is found, increase quantity of this product.
     * @param prod_ID product ID of this product.
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
     * Decreases the quantity of a product in this list
     * Transverse through grocery list.
     * Once a match is found, decrease quantity of this product.
     * @param prod_ID product ID of this product.
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
     * Removes a product from this grocery list.
     * Makes the quantity of this product in this list 0.
     * @param prod_ID product ID of this product.
     */
    public void removeProduct(int prod_ID) {
        for (int i = 0; i < ArrayProduct.length; i++){
            if (prod_ID == ArrayProduct[i][0]){
                ArrayProduct[i][1] = 0;
            }
        }
    }

    /**
     * @param prod_ID product ID of this product.
     * @return result on whether product is inside this grocery list.
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

