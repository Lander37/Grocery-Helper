package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.ActionBar;
import android.widget.TabHost;

import com.example.myfirstapp.R;

public class CartActivity extends AppCompatActivity {

    private Button btAddList;
    private TabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btAddList = (Button) findViewById(R.id.addList);

        btAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity();
            }
        });





    }
    private void launchActivity() {

        Intent intent = new Intent(this, SelectProductCategoryActivity.class);
        startActivity(intent);
    }

}
