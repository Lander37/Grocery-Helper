package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstapp.R;

public class SelectCategoryFragment extends Fragment {

    private int gl_id;
    private Button btLocation;
    private Button btFilter;
    private SearchView prodSearch;

    private Button btBeverages;
    private Button btCereal;
    private Button btDiary;
    private Button btFats;
    private Button btFruits;
    private Button btIceCream;
    private Button btMeat;
    private Button btSauces;
    private Button btSeafood;
    private Button btGluten;
    Button btDone;
    Button btBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        gl_id = args.getInt("gl_id");

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_select_product_category, container, false);
        btLocation = (Button) view.findViewById(R.id.LocationP);
        btFilter = (Button) view.findViewById(R.id.FilterP);
        btBack = (Button) view.findViewById(R.id.back);
        btDone = (Button) view.findViewById(R.id.done);
        btBeverages = (Button) view.findViewById(R.id.beverages);
        btCereal = (Button) view.findViewById(R.id.cereal);
        btDiary = (Button) view.findViewById(R.id.diary);
        btFats = (Button) view.findViewById(R.id.fats);
        btFruits = (Button) view.findViewById(R.id.fruits);
        btIceCream = (Button) view.findViewById(R.id.iceCream);
        btMeat = (Button) view.findViewById(R.id.meat);
        btSauces = (Button) view.findViewById(R.id.sauces);
        btSeafood = (Button) view.findViewById(R.id.seafood);
        btGluten = (Button) view.findViewById(R.id.glutenFree);

        btLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).showDialog(ChooseLocationDialog.newInstance());
            }
        });

        btFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).showDialog(FilterDietaryPrefDialog.newInstance());
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(CartFragment.newInstance(),"Cart");
            }
        });

        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id),"Cart");
            }
        });

        return view;
    }

    public static SelectCategoryFragment newInstance(int gl_id) {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        fragment.setArguments(args);
        return fragment;
    }
}
