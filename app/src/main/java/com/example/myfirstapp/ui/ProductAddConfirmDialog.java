package com.example.myfirstapp.ui;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import com.example.myfirstapp.R;
import com.example.myfirstapp.mgr.GroceryManager;

/**
 * Created by Daniel on 3/30/2017.
 */

public class ProductAddConfirmDialog extends DialogFragment {
    int product_ID;
    int groceryList_ID;
    String productName;
    GroceryManager groceryManager;


    @Override

    /**
     * Creates a bundle class to store values of product_ID, productName and groceryList
     *
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        product_ID = bundle.getInt("prod_id");
        productName = bundle.getString("prod_name");
        groceryList_ID = bundle.getInt("gl_id");
        groceryManager = new GroceryManager(getActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_product_add_1, container, false);
        TextView productDialogPrompt = (TextView) v.findViewById(R.id.product_add_dialog_1_prompt);
        FloatingActionButton productAddApprove = (FloatingActionButton) v.findViewById(R.id.product_add_dialog_1_approve);
        FloatingActionButton productAddCancel = (FloatingActionButton) v.findViewById(R.id.product_add_dialog_1_cancel);

        productDialogPrompt.setText(R.string.product_add_dialog_1_prompt_pt1 + productName + R.string.product_add_dialog_1_prompt_pt2);
        productAddApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).showDialog(ProductAddConfirmDialog2.newInstance());

            }
        });

        productAddCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).closeDialogs();
            }
        });

        return v;
    }

    public static ProductAddConfirmDialog newInstance(int prod_id, String prodName, int gl_id) {
        ProductAddConfirmDialog productAddConfirmDialog = new ProductAddConfirmDialog();

        /**
         *  Creates a bundle to store the values of product id, product name, and grocery list id
         *  for it to be transferred to another activity
         */
        Bundle args = new Bundle();
        args.putInt("prod_id", prod_id);
        args.putString("prod_name", prodName);
        args.putInt("gl_id",gl_id);
        productAddConfirmDialog.setArguments(args);

        return productAddConfirmDialog;
    }
}

