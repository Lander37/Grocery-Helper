package com.example.myfirstapp.classes;

/**
 * Created by XY on 8/3/2017.
 */



public class Supermarket {

    private String SM_ID;
    private String Location;
    private Product[] ArrayProduct;

    public String getLocation() {
        return this.Location;
    }

    /**
     *
     * @param Location
     */

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getSM_ID() {
        return this.SM_ID;
    }

    /**
     *
     * @param SM_ID
     */
    public void setSM_ID(String SM_ID) {
        this.SM_ID = SM_ID;
    }

    public Product[] getArrayProduct() {
        return this.ArrayProduct;
    }

    /**
     *
     * @param ArrayProduct
     */
    public void setArrayProduct(Product[] ArrayProduct) {
        this.ArrayProduct = ArrayProduct;
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void addProduct(String prod_ID, int QTY) {
        // TODO - implement Supermarket.addProduct
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void removeProduct(String prod_ID, int QTY) {
        // TODO - implement Supermarket.removeProduct
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod_ID
     */
    public int updateQTY(String prod_ID) {
        // TODO - implement Supermarket.updateQTY
        throw new UnsupportedOperationException();
    }

}
