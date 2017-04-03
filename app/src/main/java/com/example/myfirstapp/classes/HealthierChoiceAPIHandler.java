package com.example.myfirstapp.classes;

/**
 * HealthierChoiceAPIHandler.java - a simple class for reading a Data API and
 * inserting its data into our application using JSON method.
 * @author tosy
 * @see HttpHandler
 **/

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HealthierChoiceAPIHandler {
    private Context appContext;
    private String TAG = HealthierChoiceAPIHandler.class.getSimpleName();
    private ArrayList<String> productList;
    private boolean errorRetrievingData;
    private String errorMessage;

    public HealthierChoiceAPIHandler(Context context) {
        appContext = context;
        productList = new ArrayList<String>(0);
        errorRetrievingData = false;
        errorMessage = "No errors";

        new GetProducts().execute();
    }

    private class GetProducts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(appContext,"Json Data is downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            /**
             * Making a request to the url and getting a reply.
             * @param url - an absolute URL where we got our data API from.
             */
            String url = "https://data.gov.sg/api/action/datastore_search?resource_id=6bf1e41f-cdf8-47ca-ac72-c5c076f59416";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    /**Code for retrieving JSON Array.
                     * @param records the location of the data.
                     */
                    JSONArray products = jsonObj.getJSONObject("result").getJSONArray("records");

                    /**Loops through all the products.
                     * @param i the total number of products in this list.
                     */
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        //String _id = c.optString("id");
                        //String main_food_group = c.optString("main_food_group");
                        //String company_name = c.optString("company_name");
                        String product_name = c.optString("product_name");
                        //String brand_name = c.optString("brand_name");
                        //String product_weight = c.optString("product_weight");

                        // tmp hash map for single contact
                        //HashMap<String, String> product = new HashMap<>();

                        // adding each child node to HashMap key => value
                        // product.put("id", _id);
                        //product.put("main_food_group", main_food_group);
                        //product.put("company_name", company_name);
                        productList.add(product_name);
                        // product.put("brand name", brand_name);
                        // product.put("product weight", product_weight);
                        //productList.add(product);

                        /**Adds products into this product list.
                         * @param product item that can be found in this list.
                         */
                        //productList.add(product);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    errorRetrievingData = true;
                    errorMessage = "Json parsing error: " + e.getMessage();
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                errorRetrievingData = true;
                errorMessage = "Couldn't get json from server. Possible connection issues!";
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            if(!errorRetrievingData){
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(appContext);
                databaseAccess.open();
                //databaseAccess.updateProductHealthierChoice(productList);
                databaseAccess.close();
            } else {
                Toast.makeText(appContext, errorMessage , Toast.LENGTH_LONG).show();
            }
        }

        public ArrayList<String> getProductList(){
            return productList;
        }
    }


}