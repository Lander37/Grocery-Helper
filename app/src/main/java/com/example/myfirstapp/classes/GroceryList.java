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
        return this.Name;
    }

    /**
     *
     * @param Name
     */
    public void setName(String Name) {

        this.Name = Name;
    }

    public float getTotalCost() {
        return this.TotalCost;

    }

    /**
     *
     * @param TotalCost
     */
    public void setTotalCost(float TotalCost) {
        this.TotalCost = TotalCost;
    }

    public Calendar getDate() {
        return this.creationDate;
    }

    /**
     *
     * @param creationDate
     */
    public void setDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public int getGL_ID() {
        return this.GL_ID;
    }

    /**
     *
     * @param GL_ID
     */
    public void setGL_ID(int GL_ID) {
        this.GL_ID = GL_ID;
    }

    public Product[] getArrayProduct() {

        // TODO - implement GroceryList.getArrayProduct

        throw new UnsupportedOperationException();

    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */

    public void setArrayProduct(int[][] ArrayProduct) {
        this.ArrayProduct = ArrayProduct;
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void addProdToList(int prod_ID, int QTY) {
        int i = 0;
        while (true){

                if (ArrayProduct[i][1] == 0) {
                    ArrayProduct[i][0] = prod_ID;
                    ArrayProduct[i][1] = QTY;
                    break;
                }

                i++;
        }
    }

}
