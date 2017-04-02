package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.myfirstapp.R;

public class ProfileFragment extends Fragment {

    private Button btLogOut;
    private Button btCloseApp;
    private EditText etEditUsername;
    private EditText etEditPassword;
    private Spinner spLocationList;
    private SeekBar sbHealthSeekBar;
    private CheckBox tbHealthierChoice;
    private CheckBox tbHalal;
    private CheckBox tbVegetarian;
    private CheckBox tbGluten;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btLogOut = (Button) view.findViewById(R.id.logOut);
        btLogOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).setBtLogOut();
            }
        });

        etEditUsername = (EditText) view.findViewById(R.id.editUserName);
        etEditPassword = (EditText) view.findViewById(R.id.editPassword);

        spLocationList = (Spinner) view.findViewById(R.id.locationList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocationList.setAdapter(adapter);

        sbHealthSeekBar = (SeekBar) view.findViewById(R.id.healthSeekBar);
        tbHealthierChoice = (CheckBox) view.findViewById(R.id.healthierChoiceButton);
        tbHalal = (CheckBox) view.findViewById(R.id.halalButton);
        tbVegetarian = (CheckBox) view.findViewById(R.id.vegetarianButton);
        tbGluten = (CheckBox) view.findViewById(R.id.glutenButton);


        return view;
    }


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }




}
