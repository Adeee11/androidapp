package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DeleteAccount extends AppCompatActivity {
    Spinner deleteAccSpinner;
    Button deleteAccButton, backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        //binding variables to screen elements
        deleteAccSpinner = findViewById(R.id.deleteAccountSpinner);
        deleteAccButton = findViewById(R.id.deleteAccbtn);
        backBtn = findViewById(R.id.gobackDelete);
        // adding account numbers to spinner
        deleteAccSpinner.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_item,User.findUserAccountNumbers()));
        deleteAccButton.setOnClickListener((View v)-> {
            try {
                long accNum = Long.parseLong(deleteAccSpinner.getSelectedItem().toString());
                // delete account
                String msg = User.currentUser.deleteAccount(getApplicationContext(), accNum);

                Helpers.showToast(getApplicationContext(),msg);
                //update spinner
                deleteAccSpinner.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_item,User.findUserAccountNumbers()));

            }catch (Exception e){
                Helpers.showToast(getApplicationContext(), "Something Went Wrong. Contact Support");
            }
        });

        backBtn.setOnClickListener((View v)-> {
            finish();
        });

    }

}