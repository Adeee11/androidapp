package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    EditText accessInput, pin;
    Button signInBtn, signUpBtn;

    public void fillUsers(){
        // check if users are in memory if not then get them from external storage
        if(User.allUsers.size() == 0){
           ArrayList<User> users =  Helpers.getAllUsers(getApplicationContext());
           Log.d("Works", "test log");
           if(users.size() > 0){
               User.allUsers = users;
           }else {
               // create some defaults user for testing application
               User adarsh = new User(10010010,1312, "Adarsh", 8727880220L);
               adarsh.accounts.add(new Account(10010010,100010,
                       100.0,"savings",0.0));
               User syed = new User(10010011,1000, "Syed", 7727880220L);
               syed.accounts.add(new Account(10010011,100012,
                       1000.0,"current",0.0));
               User.allUsers.add(adarsh);
               User.allUsers.add(syed);
               Helpers.saveAllUsers(getApplicationContext(),User.allUsers);
           }
        }
        Log.d("User", User.allUsers.get(0).name);
    }

    /**
     * Function to check Write Permission On android
     * @return
     */
    private boolean checkWriteExternalPermission()
    {
        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        String permission2 = android.Manifest.permission.READ_EXTERNAL_STORAGE;
        int res2 = getApplicationContext().checkCallingOrSelfPermission(permission2);
        return (res == PackageManager.PERMISSION_GRANTED && PackageManager.PERMISSION_GRANTED == res2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // check permission and ask for permission otherwise
        if(!checkWriteExternalPermission()){
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE}, 1);
        }
        // get users from storage
        fillUsers();
        signInBtn = findViewById(R.id.signinBtn);
        accessInput = findViewById(R.id.accesscard);
        pin = findViewById(R.id.pin);
        signUpBtn = findViewById(R.id.gotoSignUp);

        signInBtn.setOnClickListener((View view) -> {
            try {
                // check signin success;
                Boolean success = User.signIn(Long.parseLong(accessInput.getText().toString()),
                        Integer.parseInt(pin.getText().toString()));
                if(success){
                    // go To AllOptions Screen
                    Toast.makeText(getApplicationContext(),"Sign In Successfull", Toast.LENGTH_SHORT).show();
                    Intent allOptionIntent = new Intent(getApplicationContext(), AllAccountOptions.class);
                    startActivity(allOptionIntent);
                }else {
                    // show toast login failed
                    Toast.makeText(getApplicationContext(), "Invalid Access Number or Pin", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                // show error toast
                Toast.makeText(getApplicationContext(), "Invalid Access Number or Pin", Toast.LENGTH_SHORT).show();
            }

        });
        signUpBtn.setOnClickListener((View v) -> {
            Intent signUpIntent = new Intent(getApplicationContext(), Signuppage.class);
            startActivity(signUpIntent);
        });
    }
}