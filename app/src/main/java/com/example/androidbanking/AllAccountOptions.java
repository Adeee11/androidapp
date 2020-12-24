package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllAccountOptions extends AppCompatActivity {
    Button deleteAcc, createAcc, transferMoney, payBills, logoutBtn, depositBtn, updateDetails, accSummaryBtn;

    // Helper methon to remove repeated code
    public void navigate(Class<?> cls){
        Intent i = new Intent(getApplicationContext(),cls);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_account_options);
        //binding variables to buttons
        deleteAcc = findViewById(R.id.deleteAccABtn);
        createAcc = findViewById(R.id.createAccbtn);
        transferMoney = findViewById(R.id.transbtn);
        payBills = findViewById(R.id.paybillsbtn);
        logoutBtn = findViewById(R.id.signOutBtn);
        depositBtn = findViewById(R.id.depositbtn);
        updateDetails = findViewById(R.id.updateDetailsbtn);
        accSummaryBtn = findViewById(R.id.accSummary);

        //binding events for navigation to various screens
        deleteAcc.setOnClickListener((View v) -> {
            navigate(DeleteAccount.class);
        });
        createAcc.setOnClickListener((View v) -> {
            navigate(CreateAccount.class);
        });
        transferMoney.setOnClickListener((View v) -> {
            navigate(TransferMoney.class);
        });

        payBills.setOnClickListener((View v) -> {
            navigate(PayUtils.class);
        });
        accSummaryBtn.setOnClickListener((View v) -> {
            navigate(AccountSummary.class);
        });
        depositBtn.setOnClickListener((View v) -> {
            navigate(DepostMoney.class);
        });
        updateDetails.setOnClickListener((View v) -> {
            navigate(UpdatePersonalDetails.class);
        });
        logoutBtn.setOnClickListener((View v) -> {
            User.currentUser = null;
            finish();
        });



    }
}