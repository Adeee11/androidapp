package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdatePersonalDetails extends AppCompatActivity {
    EditText name, contact;
    Button backBtn, updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_details);

        name = findViewById(R.id.updatePName);
        contact = findViewById(R.id.updatePContact);

        backBtn = findViewById(R.id.goBackUpdatePer);
        updateBtn = findViewById(R.id.updateDetButton);

        name.setText(User.currentUser.name);
        contact.setText(String.valueOf(User.currentUser.phone));
        backBtn.setOnClickListener((View v)-> {
            finish();
        });
        updateBtn.setOnClickListener((View v)-> {
            String nameStr = name.getText().toString();
            long phoneNum = Long.parseLong(contact.getText().toString());
            String msg = User.currentUser.updateDetails(getApplicationContext(),nameStr,phoneNum);
            Helpers.showToast(getApplicationContext(),msg);
        });


    }


}