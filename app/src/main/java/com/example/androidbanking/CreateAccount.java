package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class CreateAccount extends AppCompatActivity {
    EditText newAccNum;
    RadioButton savings, current;
    Button createBtn, backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //binding variables to screen elements
        backBtn = findViewById(R.id.gobackCreateAcc);
        createBtn = findViewById(R.id.createAccbtn1);
        savings = findViewById(R.id.savingRadBtn);
        current = findViewById(R.id.currentRadBtn);
        newAccNum = findViewById(R.id.newAcc);
        // initializing savings value
        savings.setChecked(true);

        //radio click event handler
        savings.setOnClickListener((View v)-> {
            savings.setChecked(true);
            current.setChecked(false);
        });
        //radio click event handler
        current.setOnClickListener((View v)-> {
            savings.setChecked(false);
            current.setChecked(true);
        });

        //initial input with filled new accnumber
        newAccNum.setText(String.valueOf(Account.genAccNumber()));

        createBtn.setOnClickListener((View v) -> {
            // get acc number
            long accNum = Long.parseLong(newAccNum.getText().toString());
            // get type
            String type = savings.isChecked() ? "Savings": "Current";
            if(Account.findAccount(accNum) != null){
                //show msg account number is user already
                Helpers.showToast(getApplicationContext(), "Choose Another Account Number");
                return;
            }
            // create account
            Account acc = new Account(User.currentUser.uid,accNum,0d,type,0d);

            User.currentUser.accounts.add(acc);
            // save in storage
            Helpers.saveAllUsers(getApplicationContext(),User.allUsers);
            Helpers.showToast(getApplicationContext(), "Account Created");
        });



        backBtn.setOnClickListener((View v)-> {
            finish();
        });
    }
}