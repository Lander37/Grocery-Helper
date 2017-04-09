package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import java.util.List;


public class SubCategoryFragment extends Fragment {

    private int gl_id;
    private String subCategory;
    ListView lvProductList;
    DatabaseAccess databaseAccess;

    public static SubCategoryFragment newInstance(int gl_id, String subCategory) {
        SubCategoryFragment fragment = new SubCategoryFragment();
        Bundle args = new Bundle();
        args.putInt("gl_id",gl_id);
        args.putString("subCategory",subCategory);
        fragment.setArguments(args);
        return fragment;
    }

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
        View view =inflater.inflate(R.layout.fragment_sub_category, container, false);
        this.databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        lvProductList = (ListView) view.findViewById(R.id.list);
        lvProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

                /**
                 * Opens the database and subsequently add the product to the database
                 */
                String selectedProduct = (String)adapter.getItemAtPosition(position);
                databaseAccess.open();
                databaseAccess.addProduct(selectedProduct, gl_id);
                databaseAccess.close();
                Toast.makeText(getContext(), selectedProduct + "  Added!" , Toast.LENGTH_SHORT).show();

            }
        });
        databaseAccess.open();
        List<String> prodList = databaseAccess.getSubCategoryList(subCategory);
        databaseAccess.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, prodList);
        this.lvProductList.setAdapter(adapter);

        return view;
    }

}
