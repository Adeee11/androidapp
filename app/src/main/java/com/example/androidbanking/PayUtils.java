package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PayUtils extends AppCompatActivity {
    Spinner accSpinner, payUtilSp;
    EditText amount;
    Button backBtn, payBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_utils);
        // binding variables to screen elements
        accSpinner = findViewById(R.id.payutilsAccSpinner);
        payUtilSp = findViewById(R.id.payutilsSpinner);
        amount = findViewById(R.id.payUtilsAmount);
        payBtn = findViewById(R.id.payButton);
        backBtn = findViewById(R.id.payUtilsBack);
        // show account numbers in spinner
        accSpinner.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,User.findUserAccountNumbers()));
        String [] utils = {"Hydro Bill", "Gas Bill", "Phone Bill","Water Bill", "Electricity Bill"};
        // show utilities in spinner
        payUtilSp.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,utils));
        backBtn.setOnClickListener((View v)-> {
            finish();
        });

        payBtn.setOnClickListener((View v)-> {
            try {
                // get chosen acc number
                long accNum = Long.parseLong(accSpinner.getSelectedItem().toString());
                Account acc = Account.findAccount(accNum);
                String amountText = amount.getText().toString();
                // get amount value
                if(amountText.equals("")){
                    Helpers.showToast(getApplicationContext(),"Please Enter An Amount");
                    return;
                }
                Double am = Double.parseDouble(amountText);
                // handle Payment
                String msg = acc.payUtils(getApplicationContext(),payUtilSp.getSelectedItem().toString(), am);
                Helpers.showToast(getApplicationContext(),msg);
            }catch (Exception e){
                // handle any exception
                Helpers.showToast(getApplicationContext(), "Something Went Wrong. Contact Support");
            }

        });
    }
}