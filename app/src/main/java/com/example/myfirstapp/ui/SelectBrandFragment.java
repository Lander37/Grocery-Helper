package com.example.myfirstapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstapp.R;

public class SelectBrandFragment extends Fragment {



    Button btBack;
    Button btDelete;
    private int gl_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        gl_id = args.getInt("gl_id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_select_brand, container, false);
        btBack = (Button) view.findViewById(R.id.backB);
        btDelete= (Button) view.findViewById(R.id.deleteB);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity) getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id), "Cart");
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Code for deleting this item from database
                ?
                 */
                ((NavigationActivity) getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id), "Cart");
            }
        });
        return view;
    }

    public static SelectBrandFragment newInstance(int gl_id) {
        SelectBrandFragment fragment = new SelectBrandFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        fragment.setArguments(args);
        return fragment;
    }

}
