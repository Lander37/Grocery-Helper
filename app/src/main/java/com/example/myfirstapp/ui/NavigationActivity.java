package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.myfirstapp.R;

public class NavigationActivity extends AppCompatActivity {


    Fragment selectedFragment = null;
    Fragment Cart = CartFragment.newInstance();
    boolean firstExpenditure = true;
    boolean firstHistory = true;
    String tag;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_grocery_list:
                    selectedFragment = getSupportFragmentManager().findFragmentByTag("Cart");
                    tag = "Cart";
                    break;

                case R.id.menu_expenditure:
                    if(firstExpenditure=true){
                        selectedFragment = ExpenditureFragment.newInstance();
                        firstExpenditure = false;}
                    else {
                        selectedFragment = getSupportFragmentManager().findFragmentByTag("Expenditure");
                    }
                    tag = "Expenditure";
                    break;

                case R.id.menu_history:
                    if(firstHistory=true){
                        selectedFragment = HistoryFragment.newInstance();
                        firstHistory = false;}
                    else {
                        selectedFragment = getSupportFragmentManager().findFragmentByTag("History");
                    }
                    tag = "History";
                    break;

                case R.id.menu_profile:

                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment, tag);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        transaction.replace(R.id.frame_layout, Cart, "Cart");
        transaction.commit();
    }

    public void replaceGrocery(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment selectedFragment = SelectCategoryFragment.newInstance();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        transaction.add(R.id.frame_layout, selectedFragment, "Cart");
        transaction.hide(currentFragment);
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }

}
