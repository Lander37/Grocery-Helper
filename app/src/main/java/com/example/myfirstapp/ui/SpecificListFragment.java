package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.classes.ProductQty;
import com.example.myfirstapp.mgr.GroceryManager;

import java.util.ArrayList;

public class SpecificListFragment extends Fragment {
    private int gl_id;
    private GroceryList currentList;
    private GroceryManager groceryManager;
    private Fragment thisFragment;
    TextView listName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        gl_id = args.getInt("gl_id");
        groceryManager = new GroceryManager(getActivity().getApplicationContext());
        groceryManager.setCurrentListID(gl_id);
        thisFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_specific_list, container, false);
        Button addItem = (Button) view.findViewById(R.id.addItem);
        Button confirmList = (Button) view.findViewById(R.id.confirmList);
        Button backBtn = (Button) view.findViewById(R.id.back);

        groceryManager.loadGListsFromDB();
        currentList = groceryManager.getCurrentList();
        listName = (TextView) view.findViewById(R.id.specific_list_name);
        listName.setText(currentList.getName());
        replaceList2(ProductsFragment.newInstance(gl_id),"Cart");
        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(SelectCategoryFragment.newInstance(gl_id,"Beverages"),"Cart");
            }
        });

        confirmList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                groceryManager.confirmList(gl_id);
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
        return view;
    }


    public static SpecificListFragment newInstance(int gl_id) {
        SpecificListFragment fragment = new SpecificListFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        fragment.setArguments(args);
        return fragment;
    }

    public void replaceList2(Fragment fragment, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame3, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
