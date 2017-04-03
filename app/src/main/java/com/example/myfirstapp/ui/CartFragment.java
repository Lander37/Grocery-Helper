package com.example.myfirstapp.ui;

import android.content.Context;
import android.database.Cursor;
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
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

public class CartFragment extends Fragment {
    private Button btAddList;
    private DatabaseAccess databaseAccess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();

        View view = inflater.inflate(R.layout.activity_cart, container, false);
        btAddList = (Button) view.findViewById(R.id.addList);
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.overviewTable);

        // Add header row
        TableRow rowHeader = new TableRow(view.getContext());
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        Cursor cursor = databaseAccess.populateGListTable();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Read columns data
                String listName = cursor.getString(cursor.getColumnIndex("Name"));
                String totalCost = cursor.getString(cursor.getColumnIndex("TotalCost"));

                // data rows
                TableRow row = new TableRow(view.getContext());
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                String[] colText = {listName, totalCost};
                for (String text : colText) {
                    TextView tv = new TextView(this.getActivity());
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(16);
                    tv.setPadding(40, 5, 255, 5);
                    tv.setText(text);
                    row.addView(tv);
                }
                tableLayout.addView(row);

            }
        }


        databaseAccess.close();

        btAddList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).showDialog(CreateListDialog1.newInstance());
            }
        });
        return view;
    }


    public static CartFragment newInstance() {
        return new CartFragment();
    }

    public void populateGListTable() {

    }
}
