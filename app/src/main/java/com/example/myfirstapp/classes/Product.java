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

    /**
     * @param ProductID
     * @param Category
     * @param Brand
     * @param ProductName
     * @param subCategory
     * @param UnitPrice
     * @param dpId
     * @param weightOrVolume
     * @param healthRating
     */
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

    /**
     * Retrieve product ID of this product from database.
     * @return ID of this product.
     */
    public int getProductID() {
        return this.productID;
    }

    /**
     * Assigns product ID to the product.
     * @param ProductID
     */
    public void setProductID(int ProductID) {
        this.productID = ProductID;
    }

    /**
     * Retrieves category of this product from database.
     * @return category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Assigns category to this product.
     * @param Category
     */
    public void setCategory(String Category) {
        this.category = Category;
    }

    /**
     * Retrieves brand of this product from database.
     * @return brand
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Assigns brand to this product.
     * @param Brand
     */
    public void setBrand(String Brand) {
        this.brand = Brand;
    }

    /**
     * Gets product name from database
     * @return name of this product
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * Assigns product name to this product
     * @param ProductName
     */
    public void setProductName(String ProductName) {
        this.productName = ProductName;
    }

    /**
     * Gets sub category from database
     * @return SubCategory
     */
    public String getSubCategory() {
        return this.subCategory;
    }

    /**
     * Assigns sub category to this product
     * @param SubCategory
     */
    public void setSubCategory(String SubCategory) {
        this.subCategory = SubCategory;
    }

    /**
     *
     * @return
     */
    public float getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * @param UnitPrice
     */
    public void setUnitPrice(float UnitPrice) {
        this.unitPrice = UnitPrice;
    }

    /**
     *
     * @return
     */
    public int getDpId() {
        return this.dpId;
    }

    /**
     * @param dpId
     */
    public void setDp_ID(int dpId) {
        this.dpId = dpId;
    }

    /**
     *
     * @return
     */
    public int getWeightOrVolume() {
        return weightOrVolume;
    }

    /**
     * @param weightOrVolume
     */
    public void setWeightOrVolume(int weightOrVolume) {
        this.weightOrVolume = weightOrVolume;
    }

    /**
     *
     * @return
     */
    public double getHealthRating() {
        return healthRating;
    }

    /**
     * @param healthRating
     */
    public void setHealthRating(double healthRating) {
        this.healthRating = healthRating;
    }
}
