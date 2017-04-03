package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import java.util.List;

import static android.R.attr.category;

public class SelectCategoryFragment extends Fragment {

    private int gl_id;
    private Button btLocation;
    private Button btFilter;
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
    private ListView lvProductList;
    private SearchView prodSearch;
    private DatabaseAccess databaseAccess;

    Button btBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        gl_id = args.getInt("gl_id");

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());

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
        lvProductList = (ListView) view.findViewById(R.id.productList);

        databaseAccess.open();
        List<String> productList = databaseAccess.getProductList("Beverages");
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, productList);
        this.lvProductList.setAdapter(adapter);


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

        btCereal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    public static SelectCategoryFragment newInstance(int gl_id, String passed) {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        fragment.setArguments(args);
        return fragment;
    }
}
