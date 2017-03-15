package com.example.myfirstapp.classes;

/**
 * Created by XY on 8/3/2017.
 */

public class Product {

    private String productID;
    private String category;
    private String brand;
    private String productName;
    private float unitPrice;
    private DietaryPreference nutriInfo;
    private int dpId;

    public Product(String ProductID, String Category, String Brand, String ProductName, float UnitPrice, DietaryPreference NutriInfo, int dpId) {
        this.productID = ProductID;
        this.category = Category;
        this.brand = Brand;
        this.productName = ProductName;
        this.unitPrice = UnitPrice;
        this.nutriInfo = NutriInfo;
        this.dpId = dpId;
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

    public DietaryPreference getNutriInfo() {
        return this.nutriInfo;
    }

    /**
     *
     * @param Nutritional_Info
     */
    public void setNutriInfo(DietaryPreference Nutritional_Info) {
        this.nutriInfo = Nutritional_Info;
    }

    public String getProductID() {
        return this.productID;
    }

    /**
     *
     * @param ProductID
     */
    public void setProductID(String ProductID) {
        this.productID = ProductID;
    }

    public int getDpId() {
        return this.dpId;
    }

    /**
     *
     * @param dpId
     */
    public void setDP_ID(int dpId) {
        this.dpId = dpId;
    }

}
