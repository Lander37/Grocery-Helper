package com.example.myfirstapp.ui;
/**
 * HistorySpecListFragment.java
 * @author
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.classes.ProductQty;
import com.example.myfirstapp.classes.ListView4ColAdapter;
import com.example.myfirstapp.mgr.HistoryManager;

public class HistorySpecListFragment2 extends Fragment {
    private ArrayList<HashMap> list;
    private ListView4ColAdapter adapter;
    private HistoryManager manager;
    private Button backButton;
    private ListView listView;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.manager = new HistoryManager(getActivity().getApplicationContext());
        list = new ArrayList<HashMap>(0);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View view = inflater.inflate(R.layout.activity_history_spec_list, container, false);
        backButton = (Button) view.findViewById(R.id.history_back_to_main);
        listView = (ListView) view.findViewById(R.id.listview);
        populateList(bundle.getInt("list_id"), view);
        adapter = new ListView4ColAdapter(getActivity(), list);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(HistoryFragment.newInstance(),"History");
            }
        });

        listView.setAdapter(adapter);
        return view;
    }

    /* --- For individual list layout ---*/

    /**
     *
     * @param list_id
     * @param view
     */
    //Populates the table history_list_details with
    public void populateList(int list_id, View view){
        TextView listName = (TextView)view.findViewById(R.id.history_list_name);
        TextView listDate = (TextView)view.findViewById(R.id.history_list_date);
        TextView totalExpenditure = (TextView)view.findViewById(R.id.history_list_total_exp);
        GroceryList selectedList = manager.getGroceryList(list_id);
        ArrayList<ProductQty> arrayProduct = selectedList.getArrayProduct();


        listName.setText(selectedList.getName());
        listDate.setText(new SimpleDateFormat("dd-MMM-yyyy").format(selectedList.getDate()));
        String totalExp = "$" + String.format ("%.2f", selectedList.getTotalCost());
        totalExpenditure.setText(totalExp);
        for(int i = 0; i < arrayProduct.size(); i++){
            Product product = arrayProduct.get(i).getProduct();
            int product_id = product.getProductID();
            int quantity = arrayProduct.get(i).getQuantity();
            String productName = product.getProductName();
            String productBrand = product.getBrand();
            float price = product.getUnitPrice() * quantity;

            HashMap temp = new HashMap();
            temp.put(adapter.FIRST_COLUMN,productName);
            temp.put(adapter.SECOND_COLUMN,productBrand);
            temp.put(adapter.THIRD_COLUMN,quantity+"");
            temp.put(adapter.FOURTH_COLUMN,price+"");
            list.add(temp);
        }
        //adapter.notifyDataSetChanged();
    }

    /**
     *
     * @param list_id
     * @return
     */
    public static HistorySpecListFragment2 newInstance(int list_id) {
        HistorySpecListFragment2 historySpecListFragment = new HistorySpecListFragment2();
        Bundle bundle = new Bundle();
        bundle.putInt("list_id",list_id);
        historySpecListFragment.setArguments(bundle);
        return historySpecListFragment;
    }


}
