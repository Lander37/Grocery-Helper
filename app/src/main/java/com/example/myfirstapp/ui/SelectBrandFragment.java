package com.example.myfirstapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;
import com.example.myfirstapp.mgr.GroceryManager;

import java.util.ArrayList;
import java.util.List;

public class SelectBrandFragment extends Fragment {

    Button btBack;
    Button btDelete;
    TextView subCategoryView;
    TableLayout brandsListTable;
    DatabaseAccess databaseAccess;
    private int prod_id;
    private int gl_id;
    private String subCategory;
    private ArrayList<Product> prodList;
    private GroceryManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        prod_id = args.getInt("prod_id");
        gl_id = args.getInt("gl_id");
        subCategory = args.getString("subCategory");
        prodList = new ArrayList<Product>(0);
        manager = new GroceryManager(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_select_brand, container, false);
        subCategoryView = (TextView) view.findViewById(R.id.productCategory);
        btBack = (Button) view.findViewById(R.id.backB);
        btDelete= (Button) view.findViewById(R.id.deleteB);
        this.databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        brandsListTable = (TableLayout) view.findViewById(R.id.overviewTable);

        subCategoryView.setText(subCategory);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity) getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id), "Cart");
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Code for deleting this item from database
                  refer to gl_id to find right list, and subCategory to find right item.
                */
                databaseAccess.open();

                databaseAccess.deleteProductFromList(gl_id, subCategory);
                databaseAccess.refreshListCosts(gl_id,manager.getListById(gl_id).getName());
                databaseAccess.close();
                ((NavigationActivity) getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id), "Cart");
            }
        });

        databaseAccess.open();
        //Code for getting a list of brands
        prodList = databaseAccess.listProductsInSubCategory(subCategory);
        databaseAccess.close();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, prodList);
        //this.lvBrandsList.setAdapter(adapter);

        populateList(view,brandsListTable);
        return view;
    }

    private void populateList(View view, TableLayout tableLayout){
        for (int i = 0; i < prodList.size(); i++) {
            // Read columns data
            final int newProduct_id = prodList.get(i).getProductID();
            String brandName = prodList.get(i).getBrand();
            String productName = prodList.get(i).getProductName();
            int netWeight = prodList.get(i).getWeightOrVolume();
            float unitPrice = prodList.get(i).getUnitPrice();

            // data rows
            TableRow row = new TableRow(view.getContext());
            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    databaseAccess.open();
                    databaseAccess.replaceProduct(prod_id, newProduct_id, gl_id);
                    databaseAccess.refreshListCosts(gl_id,manager.getListById(gl_id).getName());
                    databaseAccess.close();
                    ((NavigationActivity)getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id),"Cart");
                }
            });

            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            String[] colText = {brandName, productName, netWeight + "", unitPrice + ""};
            for (String text : colText) {
                TextView tv = new TextView(this.getActivity());
                tv.setLayoutParams(new TableRow.LayoutParams(190,200));
                tv.setTextSize(12);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setPadding(0, 5, 60, 0);
                tv.setText(text);
                row.addView(tv);
            }
            tableLayout.addView(row);

        }
    }

    public static SelectBrandFragment newInstance(int gl_id, int prod_id,String subCategory) {
        SelectBrandFragment fragment = new SelectBrandFragment();
        Bundle args = new Bundle();
        args.putInt("prod_id",prod_id);
        args.putInt("gl_id",gl_id);
        args.putString("subCategory",subCategory);
        fragment.setArguments(args);
        return fragment;
    }
}
