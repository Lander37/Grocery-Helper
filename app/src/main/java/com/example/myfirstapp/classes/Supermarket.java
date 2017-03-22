package com.example.myfirstapp.classes;

/**
 * Created by XY on 8/3/2017.
 */



public class Supermarket {

    private int SM_ID;
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

    public int getSM_ID() {
        return this.SM_ID;

    }

    /**
     *
     * @param SM_ID
     */
    public void setSM_ID(int SM_ID) {
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
    public void test(){}
}
