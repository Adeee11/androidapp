package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class AccountSummary extends AppCompatActivity {
    ListView accSumList;
    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_summary);

        accSumList = findViewById(R.id.accountsList);
        backBtn = findViewById(R.id.goBackSummary);


        // set Adapter
        accSumList.setAdapter(new AccSummaryListAdapter(this,User.currentUser.accounts));

        // go back to main options
        backBtn.setOnClickListener((View v)-> {
            finish();
        });
    }
}