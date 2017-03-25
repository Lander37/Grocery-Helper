package com.example.myfirstapp.mgr;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Profile;
import com.example.myfirstapp.classes.Supermarket;

import java.util.ArrayList;

/**
 * Created by XY on 8/3/2017.
 */

public class ProfileManager {

    private int curProfileID;
    private ArrayList<Profile> profileArray;

    public int getCurProfileID() {
        return curProfileID;
    }
    /**
     *
     * @param curProfileID
     */
    public void setCurProfileID(int curProfileID) {
        this.curProfileID = curProfileID;
    }

    public ArrayList<Profile> getProfileArray() {
        return profileArray;
    }
    /**
     *
     * @param profileArray
     */
    public void setProfileArray(ArrayList<Profile> profileArray) {
        this.profileArray = profileArray;
    }

    /**
     *
     * @param tobeAdded
     */
    public void addProfile(Profile tobeAdded) {
        for (int i = 0; i < profileArray.size(); i++){
            if (profileArray.get(i) == null){
                profileArray.set(i, tobeAdded);
                break;
            }
        }
    }


    /**
     *
     * @param username
     * @param password
     */
    public boolean verifyUsername(String username, String password) {
        // TODO - implement ProfileManager.verifyUsername
        throw new UnsupportedOperationException();
    }

    public void updateProfile() {
        // TODO - implement ProfileManager.updateProfile
        throw new UnsupportedOperationException();
    }

    public GroceryList[] getGroceryHistory() {
        // TODO - implement ProfileManager.getGroceryHistory
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param defaultLocation
     */
    public Supermarket getSupermaket(String defaultLocation) {
        // TODO - implement ProfileManager.getSupermaket
        throw new UnsupportedOperationException();
    }
}
