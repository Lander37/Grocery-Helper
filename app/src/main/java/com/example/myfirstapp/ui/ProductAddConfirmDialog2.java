package com.example.myfirstapp.ui;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstapp.R;

/**
 * Created by Daniel on 3/30/2017.
 */

public class ProductAddConfirmDialog2 extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_add_dialog_1, container, false);
        Button productOkButton = (Button) v.findViewById(R.id.product_add_dialog_2_ok);

        productOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).closeDialogs();
            }
        });

        // Watch for button clicks.
        /*Button button = (Button)v.findViewById(R.id.show);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                //((FragmentDialog)getActivity()).showDialog();
            }
        });*/

        return v;
    }

    public static ProductAddConfirmDialog2 newInstance() {
        ProductAddConfirmDialog2 productAddConfirmDialog = new ProductAddConfirmDialog2();
        return productAddConfirmDialog;
    }
}

