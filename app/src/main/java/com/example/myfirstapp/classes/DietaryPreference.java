package com.example.myfirstapp.classes;

/**DietaryPreference.java - a class that helps indicates on whether an item is
 * halal, vegetarian, healthier choice or gluten free
 * @author Xing Yue
 */

public class DietaryPreference {

    private int dpId;
    private boolean halal;
    private boolean vegetarian;
    private boolean healthierChoice;
    private boolean glutenFree;

    /**
     * Registers a true or false value
     * @param dpId display dietary preference
     */
    public DietaryPreference(int dpId){
        this.dpId = dpId;
        this.halal = false;
        this.vegetarian = false;
        this.healthierChoice = false;
        this.glutenFree = false;
    }

    /**@param dpId
     * @param halal
     * @param vegetarian
     * @param healthierChoice
     * @param glutenFree
     */
    public DietaryPreference(int dpId, boolean halal, boolean vegetarian, boolean healthierChoice, boolean glutenFree){
        this.dpId = dpId;
        this.halal = halal;
        this.vegetarian = vegetarian;
        this.healthierChoice = healthierChoice;
        this.glutenFree = glutenFree;
    }

    public boolean getHalal() {
        return this.halal;
    }

    /**
     * set to 1 if product is halal
     * set to 0 if product is not halal
     * @param halal - is product halal?
     */
    public void setHalal(boolean halal) {
        this.halal = halal;
    }

    public boolean getVegetarian() {
        return this.vegetarian;
    }

    /**
     * set to 1 if product is vegetarian
     * set to 0 if product is not vegetarian
     * @param vegetarian - is product vegetarian?
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean getHealthierChoice() {
        return this.healthierChoice;
    }

    /**
     * set to 1 if product has healthier choice label
     * set to 0 if product does not have
     * @param healthierChoice
     * - does product have the healthier choice label?
     */
    public void setHealthierChoice(boolean healthierChoice) {
        this.healthierChoice = healthierChoice;
    }

    public boolean getGlutenFree() {
        return this.glutenFree;
    }

    /**
     * set to 1 if product is gluten free
     * set to 0 if product is not
     * @param glutenFree - is product gluten free?
     */
    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public int getDP_ID() {
        return this.dpId;
    }

    /**
     * @param dpId - (Integer) id of the dietary preference setting
     */
    public void setDP_ID(int dpId) {
        this.dpId = dpId;
    }

}
