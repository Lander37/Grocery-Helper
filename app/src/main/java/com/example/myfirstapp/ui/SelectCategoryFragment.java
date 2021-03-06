package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;


import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import java.util.List;

import static android.R.attr.category;

public class SelectCategoryFragment extends Fragment{

    private int gl_id;
    private String subCategory;
    Button btLocation;
    Button btFilter;

    Button btBeverages;
    Button btCereal;
    Button btDairy;
    Button btFats;
    Button btFruits;
    Button btIceCream;
    Button btMeat;
    Button btSauces;
    Button btSeafood;
    Button btGluten;

    private SearchView prodSearch;
    DatabaseAccess databaseAccess;


    Button btDone;
    Button btBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        gl_id = args.getInt("gl_id");
        subCategory =args.getString("subCategory");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.activity_select_product_category, container, false);
        btLocation = (Button) view.findViewById(R.id.LocationP);
        btFilter = (Button) view.findViewById(R.id.FilterP);
        btBack = (Button) view.findViewById(R.id.back);
        btDone = (Button) view.findViewById(R.id.done);
        btBeverages = (Button) view.findViewById(R.id.beverages);
        btCereal = (Button) view.findViewById(R.id.cereal);
        btDairy = (Button) view.findViewById(R.id.diary);
        btFats = (Button) view.findViewById(R.id.fats);
        btFruits = (Button) view.findViewById(R.id.fruits);
        btIceCream = (Button) view.findViewById(R.id.iceCream);
        btMeat = (Button) view.findViewById(R.id.meat);
        btSauces = (Button) view.findViewById(R.id.sauces);
        btSeafood = (Button) view.findViewById(R.id.seafood);
        btGluten = (Button) view.findViewById(R.id.glutenFree);


        replaceList(SubCategoryFragment.newInstance(gl_id,subCategory),"Cart");


        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity) getActivity()).showDialog(ChooseLocationDialog.newInstance());
            }
        });

        btFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity) getActivity()).showDialog(FilterDietaryPrefDialog.newInstance());
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity) getActivity()).replaceThis(CartFragment.newInstance(), "Cart");
            }
        });

        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity) getActivity()).replaceThis(SpecificListFragment.newInstance(gl_id), "Cart");

            }
        });

        btBeverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Beverages"), "Cart");
            }
        });

        btCereal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Cereal"), "Cart");
            }
        });

        btDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Dairy Products"), "Cart");
            }
        });

        btFats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Fats and Oils"), "Cart");
            }
        });

        btFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Fruit and Vegetables"), "Cart");
            }
        });

        btIceCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Ice Cream"), "Cart");
            }
        });

        btMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Meat and Poultry"), "Cart");
            }
        });

        btSauces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Sauces, Soups and Recipe Mixes"), "Cart");
            }
        });

        btSeafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Seafood"), "Cart");

            }
        });

        btGluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceList(SubCategoryFragment.newInstance(gl_id,"Gluten-Free"), "Cart");

            }
        });

        return view;
    }

    /**
     *
     * @param gl_id
     * @param subCategory
     * @return
     */
    public static SelectCategoryFragment newInstance(int gl_id, String subCategory) {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        args.putString("subCategory",subCategory);
        fragment.setArguments(args);
        return fragment;
    }

    public void replaceList(Fragment fragment, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame2, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
