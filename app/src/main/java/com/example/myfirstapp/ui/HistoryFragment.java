package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.HistoryManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HistoryFragment extends Fragment {

    private AppCompatSpinner spinner;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private HistoryManager manager;
    private ArrayList<GroceryList> gListArray;
    private ArrayList<String> gListDescriptions;
    private ArrayAdapter<String> gListAdapter;
    private ListView groceryLists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.manager = new HistoryManager(getActivity().getApplicationContext());

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history, container, false);
        gListArray = this.manager.getgListArray();
        gListDescriptions = new ArrayList<String>(0);
        spinner = (AppCompatSpinner) view.findViewById(R.id.history_sorting_spinner);
        groceryLists = (ListView) view.findViewById(R.id.history_main_list);
        gListAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,gListDescriptions);
        groceryLists.setAdapter(gListAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sortingBy = adapterView.getItemAtPosition(i).toString();
                showHistory(sortingBy);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        groceryLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((NavigationActivity)getActivity()).replaceThis(HistorySpecListFragment.newInstance(gListArray.get(i).getGL_ID()),"History");
            }
        });

        return view;
    }

    /* --- For main history layout ---*/
    //Sorts gListArray by category: Date, quantity, total expenditure
    private void sortGroceryListArray(String sortingBy){

        if(sortingBy == getString(R.string.sortby_Date_earliest)){
            Collections.sort(gListArray, new Comparator<GroceryList>() {
                @Override
                public int compare(GroceryList gList1, GroceryList gList2)
                {

                    return  gList1.getDate().getTime().compareTo(gList2.getDate().getTime());
                }
            });
        } else if(sortingBy == getString(R.string.sortby_Date_latest)){
            Collections.sort(gListArray, new Comparator<GroceryList>() {
                @Override
                public int compare(GroceryList gList1, GroceryList gList2)
                {

                    return  -gList1.getDate().getTime().compareTo(gList2.getDate().getTime());
                }
            });
        } else if (sortingBy == getString(R.string.sortby_Quantity)){
            Collections.sort(gListArray, new Comparator<GroceryList>() {
                @Override
                public int compare(GroceryList gList1, GroceryList gList2)
                {
                    int gListQty1 = 0;
                    int gListQty2 = 0;

                    for(int i = 0; i < gList1.getArrayProduct().length; i++){
                        gListQty1 += gList1.getArrayProduct()[i][1];
                    }

                    for(int i = 0; i < gList2.getArrayProduct().length; i++){
                        gListQty2 += gList2.getArrayProduct()[i][1];
                    }

                    if(gListQty1 < gListQty2){
                        return -1;
                    } else if (gListQty1 > gListQty2){
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

        } else if (sortingBy == getString(R.string.sortby_Price)){
            Collections.sort(gListArray, new Comparator<GroceryList>() {
                @Override
                public int compare(GroceryList gList1, GroceryList gList2)
                {
                    if(gList1.getTotalCost() < gList2.getTotalCost()){
                        return -1;
                    } else if(gList1.getTotalCost() > gList2.getTotalCost()){
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }
    }

    public void showHistory(String sortingBy){
        sortGroceryListArray(sortingBy);
        gListDescriptions.clear();

        for(int i = 0; i < gListArray.size(); i++){
            gListDescriptions.add(i,"List: " + gListArray.get(i).getName() + "\n Total spent: "  + gListArray.get(i).getTotalCost());
        }

        gListAdapter.notifyDataSetChanged();
    }


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }


}
