package com.example.myfirstapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {
    private int gl_id;
    private GroceryList currentList;
    private GroceryManager groceryManager;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        gl_id = args.getInt("gl_id");
        groceryManager = new GroceryManager(getActivity().getApplicationContext());
        groceryManager.setCurrentListID(gl_id);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_products, container, false);
        TableLayout listTable = (TableLayout) view.findViewById(R.id.specific_list_details);
        currentList = groceryManager.getCurrentList();
        updateTable(view,listTable);
        return view ;
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
            price.setText((quantity*product.getUnitPrice())+"");
            quantityPicker.setMinValue(1);
            quantityPicker.setMaxValue(100);
            quantityPicker.setValue(quantity);

            quantityPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    groceryManager.updateItemQty(product_id, i1);
                    replaceList2(ProductsFragment.newInstance(gl_id),"Cart");
                }
            });

            row.setLayoutParams(new TableRow.LayoutParams(listTable.getLayoutParams().MATCH_PARENT,listTable.getLayoutParams().MATCH_PARENT));
            brand.setLayoutParams(new TableRow.LayoutParams(200,row.getLayoutParams().MATCH_PARENT));
            item.setLayoutParams(new TableRow.LayoutParams(200,row.getLayoutParams().MATCH_PARENT));
            quantityPicker.setLayoutParams(new TableRow.LayoutParams(200,100));
            price.setLayoutParams(new TableRow.LayoutParams(200,row.getLayoutParams().MATCH_PARENT));

            row.addView(item);
            row.addView(brand);
            row.addView(quantityPicker);
            row.addView(price);
            listTable.addView(row);
        }
    }

    public static ProductsFragment newInstance(int gl_id) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        fragment.setArguments(args);
        return fragment;
    }

    public void replaceList2(Fragment fragment, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame4, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
