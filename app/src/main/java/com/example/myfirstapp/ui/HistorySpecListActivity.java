package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.HistoryManager;

import org.w3c.dom.Text;

public class HistorySpecListActivity extends AppCompatActivity {
    private HistoryManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_spec_list);
        this.manager = new HistoryManager();
        populateList(savedInstanceState.getInt("list_id"));
    }

    /* --- For individual list layout ---*/

    //Populates the table history_list_details with
    public void populateList(int list_id){
        TableLayout listTable = (TableLayout)findViewById(R.id.history_list_details);
        TextView listName = (TextView)findViewById(R.id.history_list_name);
        TextView listDate = (TextView)findViewById(R.id.history_list_date);
        TextView totalExpenditure = (TextView)findViewById(R.id.history_list_total_exp);
        GroceryList selectedList = manager.getGroceryList(list_id);
        int[][] arrayProduct = selectedList.getArrayProduct();

        listName.setText(selectedList.getName());
        //listDate.setText(selectedList.getDate().getTime());
        String totalExp = "$" + String.format ("%.2f", selectedList.getTotalCost());
        totalExpenditure.setText(totalExp);
        for(int i = 0; i < arrayProduct.length; i++){
            if(arrayProduct[i][1] != 0){
                int product_id = arrayProduct[i][0];
                TableRow row = new TableRow(getBaseContext());
                TextView brand = new TextView(getBaseContext());
                TextView item = new TextView(getBaseContext());
                TextView quantity = new TextView(getBaseContext());
                TextView price = new TextView(getBaseContext());

                brand.setText("");
                item.setText("");
                quantity.setText(arrayProduct[i][1]);
                price.setText("");

                row.setLayoutParams(new TableRow.LayoutParams(listTable.getLayoutParams().MATCH_PARENT,listTable.getLayoutParams().MATCH_PARENT));
                brand.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,3));
                item.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,5));
                quantity.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,1));
                price.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,1));

                row.addView(brand);
                row.addView(item);
                row.addView(quantity);
                row.addView(price);
                listTable.addView(row);
            }
        }
    }

}
