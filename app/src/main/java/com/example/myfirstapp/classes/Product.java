package com.example.myfirstapp.classes;

/**
 * Created by XY on 8/3/2017.
 */

public class Product {

    private int productID;
    private String category;
    private String brand;
    private String productName;
    private String subCategory;
    private float unitPrice;
    private int dpId;
    private int weightOrVolume;
    private double healthRating;

    public Product(int ProductID, String Category, String Brand, String ProductName, String subCategory,
                   float UnitPrice, int dpId, int weightOrVolume, double healthRating) {
        this.productID = ProductID;
        this.category = Category;
        this.brand = Brand;
        this.productName = ProductName;
        this.subCategory = subCategory;
        this.unitPrice = UnitPrice;
        this.dpId = dpId;
        this.weightOrVolume = weightOrVolume;
        this.healthRating = healthRating;
    }

    public int getProductID() {
        return this.productID;
    }

    /**
     *
     * @param ProductID
     */
    public void setProductID(int ProductID) {
        this.productID = ProductID;
    }

    public String getCategory() {
        return this.category;
    }

    /**
     *
     * @param Category
     */
    public void setCategory(String Category) {
        this.category = Category;
    }

    public String getBrand() {
        return this.brand;
    }

    /**
     *
     * @param Brand
     */
    public void setBrand(String Brand) {
        this.brand = Brand;
    }

    public String getProductName() {
        return this.productName;
    }

    /**
     *
     * @param ProductName
     */
    public void setProductName(String ProductName) {
        this.productName = ProductName;
    }

    public String getSubCategory() {
        return this.subCategory;
    }

    /**
     *
     * @param SubCategory
     */
    public void setSubCategory(String SubCategory) {
        this.subCategory = SubCategory;
    }

    public float getUnitPrice() {
        return this.unitPrice;
    }

    /**
     *
     * @param UnitPrice
     */
    public void setUnitPrice(float UnitPrice) {
        this.unitPrice = UnitPrice;
    }

    public int getDpId() {
        return this.dpId;
    }

    /**
     *
     * @param dpId
     */
    public void setDp_ID(int dpId) {
        this.dpId = dpId;
    }

    public int getWeightOrVolume() {
        return weightOrVolume;
    }

    /**
     *
     * @param weightOrVolume
     */
    public void setWeightOrVolume(int weightOrVolume) {
        this.weightOrVolume = weightOrVolume;
    }

    public double getHealthRating() {
        return healthRating;
    }

    /**
     *
     * @param healthRating
     */
    public void setHealthRating(double healthRating) {
        this.healthRating = healthRating;
    }
}
