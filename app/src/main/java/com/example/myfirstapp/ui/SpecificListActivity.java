package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myfirstapp.R;

public class SpecificListActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState restores activities to a previous state
     *                           using the data stored in this savedInstanceState
     *                           bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_list);

    }

}
