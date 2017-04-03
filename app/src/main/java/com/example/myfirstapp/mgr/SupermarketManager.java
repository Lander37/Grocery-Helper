package com.example.myfirstapp.mgr;

import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.classes.Supermarket;

import java.util.ArrayList;

/**
 * Created by Lander on 21-Mar-17.
 */

public class SupermarketManager {

    private int currentSM_ID;
    private ArrayList<Supermarket> smArray;

    public int getcurrentSM_ID() {
        return this.currentSM_ID;
    }

    /**
     *
     * @param SM_ID
     */
    public void setcurrentSM_ID(int SM_ID) {
        this.currentSM_ID = SM_ID;
    }

    public ArrayList<Supermarket> getsmArray () {
        return this.smArray;
    }

    /**
     *
     * @param smArray
     */
    public void setsmArray(ArrayList<Supermarket> smArray) {
        this.smArray = smArray;
    }

    /**
     *
     * @param tobeAdded
     */
    public void addSupermarket(Supermarket tobeAdded) {
        smArray.add(tobeAdded);
    }

    /**
     *
     * @return
     */
    //Get Full Product List of Current Supermarket
    public Product[] findSMProductList(){
        int i = 0;
        while (i < smArray.size()){
            if (currentSM_ID == smArray.get(i).getSM_ID())
                break;
            i++;
        }
        return smArray.get(i).getArrayProduct();
    }
}
