package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.HistoryManager;

public class HistorySpecListActivity extends AppCompatActivity {
    private HistoryManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_spec_list);
        this.manager = new HistoryManager();
    }

    /* --- For individual list layout ---*/

    public void populateList(int list_id){
        GroceryList selectedList = manager.getGroceryList(list_id);
        int[][] arrayProduct = selectedList.getArrayProduct();
        for(int i = 0; i < arrayProduct.length; i++){
            if(arrayProduct[i][1] != 0){
                int product_id = arrayProduct[i][0];
                TextView row = new TextView(getApplicationContext());
            }
        }
    }

}
