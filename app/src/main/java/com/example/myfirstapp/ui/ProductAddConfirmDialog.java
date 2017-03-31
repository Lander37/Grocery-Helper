package com.example.myfirstapp.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.example.myfirstapp.R;

/**
 * Created by Daniel on 3/30/2017.
 */

public class ProductAddConfirmDialog extends DialogFragment {
    int product_ID;
    String productName;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static ProductAddConfirmDialog newInstance(int prod_id, String prodName) {
        ProductAddConfirmDialog f = new ProductAddConfirmDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("prod_id", prod_id);
        args.putString("prod_name", prodName);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product_ID = getArguments().getInt("prod_id");
        productName = getArguments().getString("prod_name");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_add_dialog_1, container, false);
        View productDialogPrompt = v.findViewById(R.id.product_add_dialog_1_prompt);
        ((TextView)productDialogPrompt).setText(R.string.product_add_dialog_1_prompt_pt1 + productName + R.string.product_add_dialog_1_prompt_pt2);

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
}

