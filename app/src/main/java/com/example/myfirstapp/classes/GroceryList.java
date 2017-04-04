package com.example.myfirstapp.classes;
import com.example.myfirstapp.mgr.SupermarketManager;

import java.util.ArrayList;
import java.util.Date;

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
    private Date creationDate;
    /**
     * Stores the productID and quantity of products in a list.
     */

    // Stores ProductID & Quantity
    private ArrayList<ProductQty> ArrayProduct;

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
        this.creationDate = new Date();
        this.ArrayProduct = new ArrayList<ProductQty>(0);
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

    public void setTotalCost(float totalCost){
        //Overload for instant set totalCost
        this.TotalCost = totalCost;
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
        for (int i = 0; i < ArrayProduct.size(); i++) {
            int prod_ID = ArrayProduct.get(i).getProduct().getProductID();
            Product[] allProductsInSM = smManager.findSMProductList();
            //Get UnitPrice from ProductID and add (Price*Qty) to TotalCost of GroceryList
            for (int j = 0; j < allProductsInSM.length; j++) {
                if (prod_ID == allProductsInSM[j].getProductID()) {
                    this.TotalCost += (allProductsInSM[j].getUnitPrice() * ArrayProduct.get(i).getQuantity());
                }
            }
        }
    }


    public void setQty(int prod_ID, int QTY){
        for(int i=0;i<ArrayProduct.size();i++){
            if(ArrayProduct.get(i).getProduct().getProductID() == prod_ID){
                ArrayProduct.get(i).setQuantity(QTY);
            }
        }
    }

    public Date getDate() {
        return this.creationDate;
    }

    /**
     * Gets the current date from Calender
     * and assign it to the grocery list.
     * @param creationDate date this grocery list is created.
     */
    public void setDate(Date creationDate) {
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

    public ArrayList<ProductQty> getArrayProduct() {
        return this.ArrayProduct;
    }

    /**
     * @param ArrayProduct
     */

    public void setArrayProduct(ArrayList<ProductQty> ArrayProduct) {
        this.ArrayProduct = ArrayProduct;
    }

    /**
     * Add Product and quantity of this product to array list
     * @param product ID of this product
     * @param QTY quantity of this product in grocery list
     */
    public void addProdToList(Product product, int QTY) {
        ProductQty newProduct = new ProductQty(product,QTY);
        ArrayProduct.add(newProduct);
    }

    /**
     * Increases the quantity of a product in this list.
     * Transverse through grocery list.
     * Once a match is found, increase quantity of this product.
     * @param prod_ID product ID of this product.
     */

    public void increaseQty(int prod_ID) {
        for (int i = 0; i < ArrayProduct.size(); i++) {
            if(ArrayProduct.get(i).getProduct().getProductID() == prod_ID){
                ArrayProduct.get(i).setQuantity(ArrayProduct.get(i).getQuantity()+1);
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
        for (int i = 0; i < ArrayProduct.size(); i++) {
            if(ArrayProduct.get(i).getProduct().getProductID() == prod_ID){
                if(ArrayProduct.get(i).getQuantity() > 1){
                ArrayProduct.get(i).setQuantity(ArrayProduct.get(i).getQuantity()-1);
                }
            }
        }
    }

    /**
     * Removes a product from this grocery list.
     * Makes the quantity of this product in this list 0.
     * @param prod_ID product ID of this product.
     */
    public void removeProduct(int prod_ID) {
        for (int i = 0; i < ArrayProduct.size(); i++){
            if (prod_ID == ArrayProduct.get(i).getProduct().getProductID()){
                ArrayProduct.remove(i);
            }
        }
    }

    /**
     * @param prod_ID product ID of this product.
     * @return result on whether product is inside this grocery list.
     */
    public boolean checkProdInList(int prod_ID){
        for (int i = 0; i < ArrayProduct.size(); i++){
            if (ArrayProduct.get(i).getProduct().getProductID() == prod_ID) {
                return true;
            }
        }
        return false;
    }
}

