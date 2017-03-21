package com.example.myfirstapp.classes;
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

    public GroceryList(String listName, int list_id){
        this.GL_ID = list_id;
        this.Name = listName;
    }

    public String getName() {
        // TODO - implement GroceryList.getName
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Name
     */
    public void setName(String Name) {
        // TODO - implement GroceryList.setName
        throw new UnsupportedOperationException();
    }

    public float getTotalCost() {
        // TODO - implement GroceryList.getTotalCost
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param TotalCost
     */
    public void setTotalCost(float TotalCost) {
        // TODO - implement GroceryList.setTotalCost
        throw new UnsupportedOperationException();
    }

    public Calendar getDate() {
        // TODO - implement GroceryList.getDate
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param creationDate
     */
    public void setDate(Calendar creationDate) {
        // TODO - implement GroceryList.setDate
        throw new UnsupportedOperationException();
    }

    public int getGL_ID() {
        // TODO - implement GroceryList.getGL_ID
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param GL_ID
     */
    public void setGL_ID(int GL_ID) {
        // TODO - implement GroceryList.setGL_ID
        throw new UnsupportedOperationException();
    }

    public int getArrayProduct() {
        // TODO - implement GroceryList.getArrayProduct
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void addProdtoList(int prod_ID, int QTY) {
        // TODO - implement GroceryList.setArrayProduct
        for (int i = 0; i < ArrayProduct.length; i++){
                if (ArrayProduct[i][1] == 0) {
                    ArrayProduct[i][0] = prod_ID;
                    ArrayProduct[i][1] = QTY;
                    break;
                }
        }
    }

}
