package com.example.myfirstapp.ui;
/**
 * HistorySpecListFragment.java
 * @author
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.classes.ProductQty;
import com.example.myfirstapp.mgr.HistoryManager;

public class HistorySpecListFragment extends Fragment {
    private HistoryManager manager;
    private Button backButton;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.manager = new HistoryManager(getActivity().getApplicationContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history_spec_list, container, false);
        backButton = (Button) view.findViewById(R.id.history_back_to_main);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(HistoryFragment.newInstance(),"History");
            }
        });

        Bundle bundle = getArguments();
        populateList(bundle.getInt("list_id"), view);
        return view;
    }

    /* --- For individual list layout ---*/

    /**
     *
     * @param list_id
     * @param view
     */
    //Populates the table history_list_details with
    public void populateList(int list_id, View view){
        TableLayout listTable = (TableLayout)view.findViewById(R.id.history_list_details);
        TextView listName = (TextView)view.findViewById(R.id.history_list_name);
        TextView listDate = (TextView)view.findViewById(R.id.history_list_date);
        TextView totalExpenditure = (TextView)view.findViewById(R.id.history_list_total_exp);
        GroceryList selectedList = manager.getGroceryList(list_id);
        ArrayList<ProductQty> arrayProduct = selectedList.getArrayProduct();


        listName.setText(selectedList.getName());
        listDate.setText(new SimpleDateFormat("dd-MMM-yyyy").format(selectedList.getDate()));
        String totalExp = "$" + String.format ("%.2f", selectedList.getTotalCost());
        totalExpenditure.setText(totalExp);
        for(int i = 0; i < arrayProduct.size(); i++){
            Product product = arrayProduct.get(i).getProduct();
            final int product_id = product.getProductID();
            int quantity = arrayProduct.get(i).getQuantity();
            TableRow row = new TableRow(view.getContext());
            TextView brand = new TextView(view.getContext());
            TextView item = new TextView(view.getContext());
            TextView quantityDisp = new TextView(view.getContext());
            TextView price = new TextView(view.getContext());

            brand.setText(product.getBrand());
            item.setText(product.getProductName());
            quantityDisp.setText(quantity+"");
            price.setText((quantity*product.getUnitPrice())+"");

            row.setLayoutParams(new TableRow.LayoutParams(listTable.getLayoutParams().MATCH_PARENT,listTable.getLayoutParams().MATCH_PARENT));

            item.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,5));
            brand.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,3));

            quantityDisp.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,1));
            price.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,1));

            row.addView(item);
            row.addView(brand);
            row.addView(quantityDisp);
            row.addView(price);
            listTable.addView(row);
        }
    }

    /**
     *
     * @param list_id
     * @return
     */
    public static HistorySpecListFragment newInstance(int list_id) {
        HistorySpecListFragment historySpecListFragment = new HistorySpecListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("list_id",list_id);
        historySpecListFragment.setArguments(bundle);
        return historySpecListFragment;
    }



}

