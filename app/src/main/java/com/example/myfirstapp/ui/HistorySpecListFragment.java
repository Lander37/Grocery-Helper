package com.example.myfirstapp.ui;

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
import com.example.myfirstapp.mgr.HistoryManager;

public class HistorySpecListFragment extends Fragment {
    private HistoryManager manager;
    private Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history_spec_list, container, false);
        this.manager = new HistoryManager();
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

    //Populates the table history_list_details with
    public void populateList(int list_id, View view){
        TableLayout listTable = (TableLayout)view.findViewById(R.id.history_list_details);
        TextView listName = (TextView)view.findViewById(R.id.history_list_name);
        TextView listDate = (TextView)view.findViewById(R.id.history_list_date);
        TextView totalExpenditure = (TextView)view.findViewById(R.id.history_list_total_exp);
        GroceryList selectedList = manager.getGroceryList(list_id);
        int[][] arrayProduct = selectedList.getArrayProduct();

        listName.setText(selectedList.getName());
        //listDate.setText(selectedList.getDate().getTime());
        String totalExp = "$" + String.format ("%.2f", selectedList.getTotalCost());
        totalExpenditure.setText(totalExp);
        for(int i = 0; i < arrayProduct.length; i++){
            if(arrayProduct[i][1] != 0){
                int product_id = arrayProduct[i][0];
                TableRow row = new TableRow(view.getContext());
                TextView brand = new TextView(view.getContext());
                TextView item = new TextView(view.getContext());
                TextView quantity = new TextView(view.getContext());
                TextView price = new TextView(view.getContext());

                brand.setText("");
                item.setText("");
                quantity.setText(arrayProduct[i][1]);
                price.setText("");

                row.setLayoutParams(new TableRow.LayoutParams(listTable.getLayoutParams().MATCH_PARENT,listTable.getLayoutParams().MATCH_PARENT));
                brand.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,3));
                item.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,5));
                quantity.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,1));
                price.setLayoutParams(new TableRow.LayoutParams(row.getLayoutParams().MATCH_PARENT,row.getLayoutParams().MATCH_PARENT,1));

                row.addView(brand);
                row.addView(item);
                row.addView(quantity);
                row.addView(price);
                listTable.addView(row);
            }
        }
    }

    public static HistorySpecListFragment newInstance(int list_id) {
        HistorySpecListFragment historySpecListFragment = new HistorySpecListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("list_id",list_id);
        historySpecListFragment.setArguments(bundle);
        return historySpecListFragment;
    }


}
