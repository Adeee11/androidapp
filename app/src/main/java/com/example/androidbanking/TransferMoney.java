package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TransferMoney extends AppCompatActivity {

    Spinner fromAccSpinner;
    EditText toAccount,amount;
    Button transferBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        // binding variables to screen elements
        fromAccSpinner = findViewById(R.id.transferAccSpinner);
        toAccount = findViewById(R.id.transferDestAcc);
        amount = findViewById(R.id.transferMoneyAmount);
        transferBtn = findViewById(R.id.transferAccBtn);
        backBtn = findViewById(R.id.transferAccback);

        ArrayAdapter accAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,User.findUserAccountNumbers());

        // fill account numbers in spinner
        fromAccSpinner.setAdapter(accAdapter);

        transferBtn.setOnClickListener((View v) -> {
            try {
                // get source account
                int fromAcc = Integer.parseInt(fromAccSpinner.getSelectedItem().toString());
                // get destination account
                int toA = Integer.parseInt(toAccount.getText().toString());
                // get amount value
                Double amounta = Double.parseDouble(amount.getText().toString());
                // handle transfer
                String msg = Account.transferMoney(getApplicationContext(),fromAcc,toA,amounta);
                Helpers.showToast(getApplicationContext(), msg);
            }catch (Exception e){
                // handle exception
                Helpers.showToast(getApplicationContext(), "Please Try Again Later");
            }

        });
        // handle back click
        backBtn.setOnClickListener((View v) -> {
            finish();
        });


    }


}