package com.example.myfirstapp.classes;
import java.util.Calendar;

/**
 *Expenditure.java - a class that contains the monthly expenditure and budget of user
 * @author calvin
 */

public class Expenditure {

    private float thisMonthBudget;
    private float thisMonthExp;
    private int month;
    private float monthlyIncome;

    /**
     *
     * @param thisMonthBudget
     * @param thisMonthExp
     * @param monthlyIncome
     */
    public Expenditure(float thisMonthBudget, float thisMonthExp, float monthlyIncome){
        Calendar c = Calendar.getInstance();
        this.month = c.get(Calendar.MONTH);
    }

    /**
     *
     * @param thisMonthBudget
     * @param thisMonthExp
     * @param month
     * @param monthlyIncome
     */
    public Expenditure(float thisMonthBudget, float thisMonthExp, int month, float monthlyIncome){
        this(thisMonthBudget, thisMonthExp, monthlyIncome);
        this.month = month;
    }

    /**
     *
     * @return budget for that particular month
     */
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
     * @param monthlyIncome - user's monthly income
     */
    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * @param monthlyIncome - user's monthly income
     * @return Budget of user according to user's input
     */
    public float calculateBudget(float monthlyIncome) {
        float factor = (float)0.4;
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