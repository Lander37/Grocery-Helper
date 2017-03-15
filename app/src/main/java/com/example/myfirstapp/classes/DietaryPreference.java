package com.example.myfirstapp.classes;

/**
 * Created by XY on 8/3/2017.
 */

public class DietaryPreference {

    private int dpId;
    private boolean halal;
    private boolean vegetarian;
    private boolean healthierChoice;
    private boolean glutenFree;

    public DietaryPreference(int dpId){
        this.dpId = dpId;
        this.halal = false;
        this.vegetarian = false;
        this.healthierChoice = false;
        this.glutenFree = false;
    }

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
     *
     * @param halal - is halal?
     */
    public void setHalal(boolean halal) {
        this.halal = halal;
    }

    public boolean getVegetarian() {
        return this.vegetarian;
    }

    /**
     *
     * @param vegetarian - is vegetarian?
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean getHealthierChoice() {
        return this.healthierChoice;
    }

    /**
     *
     * @param healthierChoice - has the healthier choice label?
     */
    public void setHealthierChoice(boolean healthierChoice) {
        this.healthierChoice = healthierChoice;
    }

    public boolean getGlutenFree() {
        return this.glutenFree;
    }

    /**
     *
     * @param glutenFree - is gluten free?
     */
    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public int getDP_ID() {
        return this.dpId;
    }

    /**
     *
     * @param dpId - (Integer) id of the dietary preference setting
     */
    public void setDP_ID(int dpId) {
        this.dpId = dpId;
    }

}
