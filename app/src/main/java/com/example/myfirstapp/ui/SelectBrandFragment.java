package com.example.myfirstapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import java.util.List;

public class SelectBrandFragment extends Fragment {



    Button btBack;
    Button btDelete;
    ListView lvBrandsList;
    DatabaseAccess databaseAccess;
    private int gl_id;
    private String subCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        gl_id = args.getInt("gl_id");

        subCategory =args.getString("subCategory");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_select_brand, container, false);
        btBack = (Button) view.findViewById(R.id.backB);
        btDelete= (Button) view.findViewById(R.id.deleteB);
        this.databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        lvBrandsList = (ListView) view.findViewById(R.id.list);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity) getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id), "Cart");
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseAccess.deleteProductFromList(gl_id, subCategory);
                ((NavigationActivity) getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id), "Cart");
            }
        });

        databaseAccess.open();
        /*Code for getting a list of brands
        List<String> prodList = databaseAccess.getBrands(subCategory); <-implement getBrands method
        */

        databaseAccess.close();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, prodList);
        //this.lvBrandsList.setAdapter(adapter);
        return view;
    }

    public static SelectBrandFragment newInstance(int gl_id, String subCategory) {
        SelectBrandFragment fragment = new SelectBrandFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        args.putString("subCategory",subCategory);
        fragment.setArguments(args);
        return fragment;
    }
}
