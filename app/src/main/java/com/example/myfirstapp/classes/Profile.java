package com.example.myfirstapp.classes;

/**
 * Created by XY on 8/3/2017.
 */

public class Profile {

    private String username;
    private String password;
    private String defaultLocation;
    private int dpId;
    private int healthEmphasis;
    private int profileID;

    /**
     * @param username name of person who is using this account.
     * @param password access key unique to this user to get access to this account.
     * @param defaultLocation location of the user's preferred area.
     * @param dpId refers to the dietary preferences of this user.
     * @param healthEmphasis scale of how healthy this user preferred his choices to be.
     * @param profileID refers to the profile of this user.
     */
    public Profile(String username, String password, String defaultLocation, int dpId, int healthEmphasis, int profileID){
        this.username = username;
        this.password = password;
        this.defaultLocation = defaultLocation;
        this.dpId = dpId;
        this.healthEmphasis = healthEmphasis;
        this.profileID = profileID;
    }

    public String getUsername() {
        return this.username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * @param Password
     */
    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getDefaultLocation() {
        return this.defaultLocation;
    }

    /**
     * @param defaultLocation
     */
    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public int getdpId() {
        return this.dpId;
    }

    /**
     * @param dpId
     */
    public void setdpId(int dpId) {
        this.dpId = dpId;
    }

    public int getHealthEmphasis() {
        return healthEmphasis;
    }
    /**
     * @param healthEmphasis
     */
    public void setHealthEmphasis(int healthEmphasis) {
        this.healthEmphasis = healthEmphasis;
    }

    public int getProfileID() {
        return profileID;
    }
    /**
     * @param profileID
     */
    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

}
