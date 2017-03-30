package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.GroceryManager;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;

public class GroceryUI extends AppCompatActivity {

    private GroceryManager manager = new GroceryManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_ui);
        //this.manager.loadGroceryUI();

/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void displayGroceryLists(GroceryList[] groceryLists){
        TableLayout overview = (TableLayout) findViewById(R.id.overview);
        for(int i = 0; i < groceryLists.length; i++){
            TableRow newRow = new TableRow(getApplicationContext());
            TextView textContent = new TextView(getApplicationContext());
            String listName = groceryLists[i].getName();
            String expenditure = groceryLists[i].getTotalCost() + "";

            textContent.setText(listName + "  " + expenditure);
            newRow.addView(textContent);
            overview.addView(newRow);
        }
    }

}
