package com.example.myfirstapp.classes;

import static android.os.Build.ID;

/**
 * Created by XY on 8/3/2017.
 */



public class Supermarket {

    private String SM_ID;
    private String location;
    private Product[][] arrayProduct;

    public String getLocation() {
        // TODO - implement Supermarket.getLocation
        return this.location
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Location
     */

    public void setLocation(String Location) {
        // TODO - implement Supermarket.setLocation
        this.location = Location
        throw new UnsupportedOperationException();
    }

    public String getSM_ID() {
        // TODO - implement Supermarket.getSM_ID
        return this.SM_ID
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param SM_ID
     */
    public void setSM_ID(String SM_ID) {
        // TODO - implement Supermarket.setSM_ID
        this.SM_ID = SM_ID
        throw new UnsupportedOperationException();
    }

    public Product[] getArrayProduct() {
        // TODO - implement Supermarket.getArrayProduct
        return this.arrayProduct =
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param ArrayProduct
     */
    public void setArrayProduct(Product[] ArrayProduct) {
        // TODO - implement Supermarket.setArrayProduct
        arrayProduct = ArrayProduct
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void addProduct(String Prod_ID, int QTY) {
        // TODO - implement Supermarket.addProduct

        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void removeProduct(String Prod_ID, int QTY) {
        // TODO - implement Supermarket.removeProduct
        prod_ID = Prod_ID
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod_ID
     */
    public int updateQTY(String prod_ID) {
        // TODO - implement Supermarket.updateQTY
        QTY = setQTY
        throw new UnsupportedOperationException();
    }

}
