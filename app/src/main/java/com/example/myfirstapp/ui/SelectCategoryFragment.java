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

    Button btLocation;
    Button btFilter;
    Button btBeverages;
    Button btCereal;
    Button btDiary;
    Button btFats;
    Button btFruits;
    Button btIceCream;
    Button btMeat;
    Button btSauces;
    Button btSeafood;
    Button btGluten;
    private SearchView prodSearch;
    Button btBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_select_product_category, container, false);
        btLocation = (Button) view.findViewById(R.id.LocationP);
        btFilter = (Button) view.findViewById(R.id.FilterP);
        btBack = (Button) view.findViewById(R.id.back);
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

        return view;
    }

    public static SelectCategoryFragment newInstance() {
        return new SelectCategoryFragment();
    }
}
