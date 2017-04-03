package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        View view = inflater.inflate(R.layout.activity_cart, container, false);
        btAddList = (Button) view.findViewById(R.id.addList);

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

}
