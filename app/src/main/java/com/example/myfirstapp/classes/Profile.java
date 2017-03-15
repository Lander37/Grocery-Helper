package com.example.myfirstapp.classes;

/**
 * Created by XY on 8/3/2017.
 */

public class Profile {

    private String username;
    private String password;
    private String defaultLocation;
    private DietaryPreference healthPreference;

    public Profile(String username, String password, String defaultLocation, DietaryPreference healthPreference){
        this.username = username;
        this.password = password;
        this.defaultLocation = defaultLocation;
        this.healthPreference = healthPreference;
    }

    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param Password
     */
    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getDefaultLocation() {
        return this.defaultLocation;
    }

    /**
     *
     * @param defaultLocation
     */
    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public DietaryPreference getHealthPreference() {
        return this.healthPreference;
    }

    /**
     *
     * @param healthPreference
     */
    public void setHealthPreference(DietaryPreference healthPreference) {
        this.healthPreference = healthPreference;
    }

}
