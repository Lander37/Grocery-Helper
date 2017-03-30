package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.Button;

import com.example.myfirstapp.R;

public class SelectProductCategoryActivity extends AppCompatActivity {

    private Button btLocation;
    private Button btFilter;
    private SearchView prodSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product_category);


    }

}
