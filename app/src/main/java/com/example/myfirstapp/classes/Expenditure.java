package com.example.myfirstapp.classes;
import java.util.Calendar;

/**
 * Created by XY on 8/3/2017.
 */

public class Expenditure {

    private float thisMonthBudget;
    private float thisMonthExp;
    private int month;
    private float monthlyIncome;

    public Expenditure(float thisMonthBudget, float thisMonthExp, float monthlyIncome){
        Calendar c = Calendar.getInstance();
        this.month = c.get(Calendar.MONTH);
    }

    public Expenditure(float thisMonthBudget, float thisMonthExp, int month, float monthlyIncome){
        this(thisMonthBudget, thisMonthExp, monthlyIncome);
        this.month = month;
    }

    public float getThisMonthBudget() {
        return this.thisMonthBudget;
    }

    /**
     *
     * @param thisMonthBudget - allowed spending budget of the user for the month
     */
    public void setThisMonthBudget(float thisMonthBudget) {
        this.thisMonthBudget = thisMonthBudget;
    }

    public float getThisMonthExp() {
        return this.thisMonthExp;
    }

    /**
     *
     * @param thisMonthExp - expenses of the user for the month
     */
    public void setThisMonthExp(float thisMonthExp) {
        this.thisMonthExp = thisMonthExp;
    }

    public int getMonth() {
        return this.month;
    }

    /**
     *
     * @param month - number of the month of the current year
     */
    public void setMonth(int month) {
        this.month = month;
    }

    public float getMonthlyIncome() {
        return this.monthlyIncome;
    }

    /**
     *
     * @param monthlyIncome - user's monthly income
     */
    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     *
     * @param monthlyIncome - user's monthly income
     */
    public float calculateBudget(float monthlyIncome) {
        float factor = 1;
        float calculatedBudget;

        calculatedBudget = factor * monthlyIncome;
        return calculatedBudget;
    }

    public void viewExpSummary() {
        // TODO - implement Expenditure.viewExpSummary
        //If this is an entity class, it shouldn't interact directly with the UI I think?
        //Maybe this function returns a string of markup text that will be transmitted to UI?
        throw new UnsupportedOperationException();
    }

}