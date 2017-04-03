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
import com.example.myfirstapp.mgr.GroceryManager;

public class SpecificListFragment extends Fragment {
    private int gl_id;
    private GroceryList currentList;
    private GroceryManager groceryManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        gl_id = args.getInt("gl_id");
        groceryManager = new GroceryManager(getActivity().getApplicationContext());
        groceryManager.setCurrentListID(gl_id);
        currentList = groceryManager.getCurrentList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_specific_list, container, false);
        Button addItem = (Button) view.findViewById(R.id.addItem);
        Button confirmList = (Button) view.findViewById(R.id.confirmList);
        Button backBtn = (Button) view.findViewById(R.id.back);
        TableLayout listTable = (TableLayout) view.findViewById(R.id.specific_list_details);

        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(SelectCategoryFragment.newInstance(gl_id),"Cart");
            }
        });

        confirmList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                groceryManager.confirmList();
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

        updateTable(view,listTable);

        return view;
    }

    private void updateTable(final View view, final TableLayout listTable){
        int[][] arrayProduct = currentList.getArrayProduct();
        for(int i = 0; i < arrayProduct.length; i++){
            if(arrayProduct[i][1] != 0){
                final int product_id = arrayProduct[i][0];
                TableRow row = new TableRow(view.getContext());
                TextView brand = new TextView(view.getContext());
                TextView item = new TextView(view.getContext());
                NumberPicker quantity = new NumberPicker(view.getContext());
                TextView price = new TextView(view.getContext());

                brand.setText("");
                item.setText("");
                price.setText("");
                quantity.setValue((arrayProduct[i][1]));
                quantity.setMaxValue(1);
                quantity.setMaxValue(99999);

                quantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        groceryManager.updateItemQty(product_id, i1);
                        updateTable(view,listTable);
                    }
                });

                row.setLayoutParams(new TableRow.LayoutParams(listTable.getLayoutParams().MATCH_PARENT,listTable.getLayoutParams().MATCH_PARENT));
                brand.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,3));
                item.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,5));
                quantity.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,1));
                price.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,1));

                row.addView(item);
                row.addView(brand);
                row.addView(quantity);
                row.addView(price);
                listTable.addView(row);
            }
        }
    }

    public static SpecificListFragment newInstance(int gl_id) {

        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        SpecificListFragment fragment = new SpecificListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
