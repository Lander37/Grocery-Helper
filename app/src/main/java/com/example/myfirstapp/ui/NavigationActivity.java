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
    Fragment Cart = CartFragment.newInstance();
    Fragment Expenditure = ExpenditureFragment.newInstance();
    Fragment selectedFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_grocery_list:
                    selectedFragment = getSupportFragmentManager().findFragmentByTag("Cart");
                    break;

                case R.id.menu_expenditure:
                    selectedFragment = getSupportFragmentManager().findFragmentByTag("Expenditure");
                    break;

                case R.id.menu_history:
                    selectedFragment = HistoryFragment.newInstance();
                    break;

                case R.id.menu_profile:

                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
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
        transaction.add(R.id.frame_layout, Cart, "Cart");
        transaction.add(R.id.frame_layout, Expenditure, "Expenditure");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        transaction.replace(R.id.frame_layout, CartFragment.newInstance());
        transaction.commit();
    }

    public void replaceGrocery(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment selectedFragment = SelectCategoryFragment.newInstance();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        transaction.add(R.id.frame_layout, selectedFragment, "Cart");
        transaction.hide(currentFragment);
        //transaction.hide(selectedFragment);
        //transaction.show(selectedFragment);

        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }

}
