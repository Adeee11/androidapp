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

        fromAccSpinner = findViewById(R.id.transferAccSpinner);
        toAccount = findViewById(R.id.transferDestAcc);
        amount = findViewById(R.id.transferMoneyAmount);
        transferBtn = findViewById(R.id.transferAccBtn);
        backBtn = findViewById(R.id.transferAccback);

        ArrayAdapter accAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,User.findUserAccountNumbers());

        fromAccSpinner.setAdapter(accAdapter);

        transferBtn.setOnClickListener((View v) -> {
            try {
                int fromAcc = Integer.parseInt(fromAccSpinner.getSelectedItem().toString());
                int toA = Integer.parseInt(toAccount.getText().toString());
                Double amounta = Double.parseDouble(amount.getText().toString());
                String msg = Account.transferMoney(getApplicationContext(),fromAcc,toA,amounta);
                Helpers.showToast(getApplicationContext(), msg);
            }catch (Exception e){
                Helpers.showToast(getApplicationContext(), "Please Try Again Later");
            }

        });

        backBtn.setOnClickListener((View v) -> {
            finish();
        });


    }


}