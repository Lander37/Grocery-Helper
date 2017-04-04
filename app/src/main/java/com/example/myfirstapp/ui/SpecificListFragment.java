package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.classes.ProductQty;
import com.example.myfirstapp.mgr.GroceryManager;

import java.util.ArrayList;

import static com.example.myfirstapp.ui.MainActivity.df;

public class SpecificListFragment extends Fragment {
    private int gl_id;
    private GroceryList currentList;
    private GroceryManager groceryManager;
    private Fragment thisFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        gl_id = args.getInt("gl_id");
        groceryManager = new GroceryManager(getActivity().getApplicationContext());
        groceryManager.setCurrentListID(gl_id);
        thisFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_specific_list, container, false);
        TextView listName = (TextView) view.findViewById(R.id.specific_list_name);
        Button addItem = (Button) view.findViewById(R.id.addItem);
        Button confirmList = (Button) view.findViewById(R.id.confirmList);
        Button backBtn = (Button) view.findViewById(R.id.back);
        TableLayout listTable = (TableLayout) view.findViewById(R.id.specific_list_details);

        groceryManager.loadGListsFromDB();
        currentList = groceryManager.getCurrentList();

        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(SelectCategoryFragment.newInstance(gl_id,"Beverages"),"Cart");
            }
        });

        confirmList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                groceryManager.confirmList(gl_id);
                ((NavigationActivity)getActivity()).showDialog(ListConfirmedDialog.newInstance());
                ((NavigationActivity)getActivity()).replaceThis(CartFragment.newInstance(),"Cart");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(CartFragment.newInstance(),"Cart");
            }
        });

        listName.setText(currentList.getName());

        updateTable(view,listTable);

        return view;
    }

    private void updateTable(final View view, final TableLayout listTable){
        ArrayList<ProductQty> arrayProduct = currentList.getArrayProduct();
        for(int i = 0; i < arrayProduct.size(); i++){
            Product product = arrayProduct.get(i).getProduct();
            final int product_id = product.getProductID();
            int quantity = arrayProduct.get(i).getQuantity();
            TableRow row = new TableRow(view.getContext());
            TextView brand = new TextView(view.getContext());
            TextView item = new TextView(view.getContext());
            NumberPicker quantityPicker = new NumberPicker(view.getContext());
            TextView price = new TextView(view.getContext());

            brand.setText(product.getBrand());
            item.setText(product.getProductName());
            price.setText(df.format((quantity*product.getUnitPrice()))+"");
            quantityPicker.setMinValue(1);
            quantityPicker.setMaxValue(100);
            quantityPicker.setValue(quantity);

            quantityPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    groceryManager.updateItemQty(product_id, i1);
                    ((NavigationActivity)getActivity()).refreshThis(thisFragment);
                }
            });

            row.setLayoutParams(new TableRow.LayoutParams(listTable.getLayoutParams().MATCH_PARENT,listTable.getLayoutParams().MATCH_PARENT));
            brand.setPadding(18,0,0,0);
            brand.setLayoutParams(new TableRow.LayoutParams(200,200));
            item.setLayoutParams(new TableRow.LayoutParams(200,200));
            quantityPicker.setLayoutParams(new TableRow.LayoutParams(100,120));
            price.setPadding(65,0,0,0);
            price.setLayoutParams(new TableRow.LayoutParams(200,200));


            row.addView(item);
            row.addView(brand);
            row.addView(quantityPicker);
            row.addView(price);
            listTable.addView(row);
        }
    }

    public static SpecificListFragment newInstance(int gl_id) {
        SpecificListFragment fragment = new SpecificListFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        fragment.setArguments(args);
        return fragment;
    }
}
