package com.example.myfirstapp.classes;
import com.example.myfirstapp.mgr.SupermarketManager;

import java.util.ArrayList;
import java.util.Calendar;
/**
 * Created by XY on 8/3/2017.
 */

public class GroceryList {

    private int GL_ID;
    private String Name;
    private float TotalCost;
    private Calendar creationDate;
    private int[][] ArrayProduct; // Stores ProductID & Quantity

    public GroceryList(String listName, int GL_ID) {
        this.GL_ID = GL_ID;
        this.Name = listName;
        this.TotalCost = 0;
        this.creationDate = Calendar.getInstance();
        this.ArrayProduct = new int[50][2];
    }

    public String getName() {
        return this.Name;
    }

    /**
     * @param Name
     */
    public void setName(String Name) {

        this.Name = Name;
    }

    public float getTotalCost() {
        return this.TotalCost;

    }

    /**
     * @param smManager
     */
    public void setTotalCost(SupermarketManager smManager) {
        //Iterate through all Product IDs in GroceryList
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

    public int getGL_ID() {
        return this.GL_ID;
    }

    /**
     * @param GL_ID
     */
    public void setGL_ID(int GL_ID) {
        this.GL_ID = GL_ID;
    }

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

    public void increaseQty(int prod_ID) {
        for (int i = 0; i < ArrayProduct.length; i++) {
            if (ArrayProduct[i][0] == prod_ID) {
                ArrayProduct[i][1] += 1;
                break;
            }
        }
    }

    public void decreaseQty(int prod_ID) {
        for (int i = 0; i < ArrayProduct.length; i++) {
            if (ArrayProduct[i][0] == prod_ID) {
                ArrayProduct[i][1] -= 1;
                break;
            }
        }
    }

    /**
     *
     * @param prod_ID
     */
    public void removeProduct(int prod_ID) {
        for (int i = 0; i < ArrayProduct.length; i++){
            if (prod_ID == ArrayProduct[i][0]){
                ArrayProduct[i][1] = 0;
            }
        }
    }

    public boolean checkProdinList(int prod_ID){
        for (int i = 0; i < ArrayProduct.length; i++){
            if (ArrayProduct[i][0] == prod_ID && ArrayProduct[i][1] != 0) {
                return true;
            }
        }
        return false;
    }
}

