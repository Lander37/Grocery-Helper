package com.example.myfirstapp.ui;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

public class NavigationActivity extends AppCompatActivity {

    Fragment currentFragment = null;
    Fragment selectedFragment = null;
    Fragment Cart = CartFragment.newInstance();
    boolean firstExpenditure = true;
    boolean firstHistory = true;
    boolean firstProfile = true;
    String tag;
    DatabaseAccess databaseAccess;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
            switch (item.getItemId()) {
                case R.id.menu_grocery_list:
                    selectedFragment = getSupportFragmentManager().findFragmentByTag("Cart");
                    tag = "Cart";
                    break;

                case R.id.menu_expenditure:
                    if(firstExpenditure){
                        selectedFragment = ExpenditureFragment.newInstance();
                        firstExpenditure = false;}
                    else {
                        selectedFragment = getSupportFragmentManager().findFragmentByTag("Expenditure");
                    }
                    tag = "Expenditure";
                    break;

                case R.id.menu_history:
                    if(firstHistory){
                        selectedFragment = HistoryFragment.newInstance();
                        firstHistory = false;}
                    else {
                        selectedFragment = getSupportFragmentManager().findFragmentByTag("History");
                    }
                    tag = "History";
                    break;

                case R.id.menu_profile:
                    if(firstProfile){
                        selectedFragment = ProfileFragment.newInstance();
                        firstProfile = false;}
                    else {
                        selectedFragment = getSupportFragmentManager().findFragmentByTag("Profile");
                    }
                    tag = "Profile";
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

        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        transaction.replace(R.id.frame_layout, Cart, "Cart");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceThis(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, fragment, tag);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showDialog(DialogFragment fragment){
        closeDialogs();
        fragment.show(getSupportFragmentManager().beginTransaction(),"dialog");
    }

    public void closeDialogs(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if(prev != null){
            transaction.remove(prev);
        }
        transaction.commit();
    }

    public void setBtLogOut() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
