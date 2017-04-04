package com.example.myfirstapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstapp.R;

public class SelectBrandFragment extends Fragment {

    private int gl_id;
    private String subCategory;

    public static SelectBrandFragment newInstance(int gl_id ,String subcategory) {
        Bundle args = new Bundle();
        args.putInt("gl_id", gl_id);
        args.putString("subCat",subcategory);
        SelectBrandFragment fragment = new SelectBrandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        gl_id = args.getInt("gl_id");
        subCategory = args.getString("subCat");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_select_brand, container, false);
        return view;
    }


}
