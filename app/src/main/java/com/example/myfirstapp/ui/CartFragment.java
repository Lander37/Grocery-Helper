package com.example.myfirstapp.ui;
/**
 * ChooseLocationDialog.java
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.GroceryManager;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    Button btAddList;
    GroceryManager groceryManager;
    private ArrayList<GroceryList> gListArray;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groceryManager = new GroceryManager(getActivity().getApplicationContext());
        gListArray = groceryManager.getgListArray();
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_cart, container, false);
        btAddList = (Button) view.findViewById(R.id.addList);
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.overviewTable);

        // Add header row
        TableRow rowHeader = new TableRow(view.getContext());
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        /*Cursor cursor = databaseAccess.populateGListTable();
        if (cursor.getCount() > 0) {*/

            for (int i = 0; i < gListArray.size(); i++) {
                // Read columns data
                String listName = gListArray.get(i).getName();
                String totalCost = gListArray.get(i).getTotalCost() + "";
                final int gl_id = gListArray.get(i).getGL_ID();

                // data rows
                TableRow row = new TableRow(view.getContext());
                row.setClickable(true);
                row.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ((NavigationActivity)getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id),"Cart");
                    }
                });

                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                String[] colText = {listName, totalCost};
                for (String text : colText) {
                    TextView tv = new TextView(this.getActivity());
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(20);
                    tv.setPadding(40, 5, 255, 5);
                    tv.setText(text);
                    row.addView(tv);
                }
                tableLayout.addView(row);

            }
        //}

        btAddList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).showDialog(CreateListDialog1.newInstance());
            }
        });
        return view;
    }

    /**
     *
     * @return
     */
    public static CartFragment newInstance() {
        return new CartFragment();
    }

    public void populateGListTable() {

    }
}
