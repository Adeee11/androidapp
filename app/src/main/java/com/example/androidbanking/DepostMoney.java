package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DepostMoney extends AppCompatActivity {
    Spinner accSpinner;
    EditText amount;
    Button backBtn, depositBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depost_money);

        accSpinner = findViewById(R.id.depositAccSpinner);
        amount = findViewById(R.id.depositAmount);
        backBtn = findViewById(R.id.depositAccBackBtn);
        depositBtn = findViewById(R.id.depositAccBtn);
        // list account numbers
        accSpinner.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                User.findUserAccountNumbers()));


        depositBtn.setOnClickListener((View v)-> {
            try {
                String amtText = amount.getText().toString();
                if(amtText.equals("")){
                    Helpers.showToast(getApplicationContext(), "Enter An Amount Please");
                    return;
                }
                Double amt = Double.parseDouble(amtText);
                long accNum = Long.parseLong(accSpinner.getSelectedItem().toString());
                // deposit money
                String msg = Account.findAccount(accNum).depositMoney(getApplicationContext(),amt);
                Helpers.showToast(getApplicationContext(), msg);
            }catch (Exception e){
                // handle Exception
                Helpers.showToast(getApplicationContext(), "Something Went Wrong. Contact Support");
            }

        });

        backBtn.setOnClickListener((View v)-> {
            finish();
        });


    }



}