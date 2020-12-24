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

        accSpinner = findViewById(R.id.payutilsAccSpinner);
        payUtilSp = findViewById(R.id.payutilsSpinner);
        amount = findViewById(R.id.payUtilsAmount);
        payBtn = findViewById(R.id.payButton);
        backBtn = findViewById(R.id.payUtilsBack);

        accSpinner.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,User.findUserAccountNumbers()));
        String [] utils = {"Hydro Bill", "Gas Bill", "Phone Bill","Water Bill", "Electricity Bill"};
        payUtilSp.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,utils));
        backBtn.setOnClickListener((View v)-> {
            finish();
        });
        payBtn.setOnClickListener((View v)-> {
            try {
                long accNum = Long.parseLong(accSpinner.getSelectedItem().toString());
                Account acc = Account.findAccount(accNum);
                String amountText = amount.getText().toString();
                if(amountText.equals("")){
                    Helpers.showToast(getApplicationContext(),"Please Enter An Amount");
                    return;
                }
                Double am = Double.parseDouble(amountText);
                String msg = acc.payUtils(getApplicationContext(),payUtilSp.getSelectedItem().toString(), am);
                Helpers.showToast(getApplicationContext(),msg);
            }catch (Exception e){
                Helpers.showToast(getApplicationContext(), "Something Went Wrong. Contact Support");
            }

        });
    }
}