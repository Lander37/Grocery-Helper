package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;
import static com.example.myfirstapp.ui.MainActivity.thisUsername;

public class LoginActivity extends AppCompatActivity {
    private EditText etEditUsername;
    private EditText etEditPassword;
    private String loginUsername;
    private String loginPassword;
    private boolean loginValid;
    Button btLogin;
    private DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        etEditUsername = (EditText) findViewById(R.id.inputUserName);
        etEditPassword = (EditText) findViewById(R.id.inputPassword);
        btLogin = (Button) findViewById(R.id.login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUsername = etEditUsername.getText().toString();
                loginPassword = etEditPassword.getText().toString();
                databaseAccess.open();
                loginValid = databaseAccess.isLoginValid(loginUsername,loginPassword);
                databaseAccess.close();

                /**
                 * Checks if the username is correct. If correct, the interface will change,
                 * otherwise, a pop-up dialog will appear to indicate that the username is
                 * invalid.
                 */

                if (loginValid){
                    thisUsername = loginUsername;
                    launchActivity();
                    databaseAccess.close();
                }
                else{
                    showDialog(InvalidLoginDialog.newInstance());
                    databaseAccess.close();
                }
            }
        });

    }

    /**
     * Starts the Navigation Activity
     */
    private void launchActivity() {

        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
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
}
