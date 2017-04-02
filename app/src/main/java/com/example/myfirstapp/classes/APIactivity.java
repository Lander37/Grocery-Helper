package com.example.myfirstapp.classes;

/**
 * Created by tosy on 2/4/2017.
 */



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myfirstapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class APIactivity extends AppCompatActivity {

    private String TAG = APIactivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();
       // lv = (ListView) findViewById(R.id.list);

        new GetProducts().execute();
    }

    private class GetProducts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(APIactivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://data.gov.sg/api/action/datastore_search?resource_id=6bf1e41f-cdf8-47ca-ac72-c5c076f59416";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray products = jsonObj.getJSONObject("result").getJSONArray("records");

                    // looping through All Contacts
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        String _id = c.optString("id");
                        String main_food_group = c.optString("main_food_group");
                        String company_name = c.optString("company_name");
                        String product_name = c.optString("product_name");
                        String brand_name = c.optString("brand_name");
                        String product_weight = c.optString("product_weight");

                        // tmp hash map for single contact
                        HashMap<String, String> product = new HashMap<>();

                        // adding each child node to HashMap key => value
                        product.put("id", _id);
                        product.put("main_food_group", main_food_group);
                        product.put("company_name", company_name);
                        product.put("product_name", product_name);
                        product.put("brand name", brand_name);
                        product.put("product weight", product_weight);
                        productList.add(product);

                        // adding contact to contact list
                        productList.add(product);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, productList);
            lv.setAdapter(adapter);
        }
    }
}