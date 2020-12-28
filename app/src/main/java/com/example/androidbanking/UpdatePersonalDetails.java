package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdatePersonalDetails extends AppCompatActivity {
    EditText name, contact, email;
    Button backBtn, updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_details);
        // binding variables to screen elements
        name = findViewById(R.id.updatePName);
        contact = findViewById(R.id.updatePContact);
        email = findViewById(R.id.updatePEmail);
        backBtn = findViewById(R.id.goBackUpdatePer);
        updateBtn = findViewById(R.id.updateDetButton);
        // initialize name field with current name of user
        name.setText(User.currentUser.name);
        // initialize contact field with current phone of user
        contact.setText(String.valueOf(User.currentUser.phone));
        // handle back button click
        backBtn.setOnClickListener((View v)-> {
            finish();
        });

        // handle Update button click
        updateBtn.setOnClickListener((View v)-> {
            try {
                // get name
                String nameStr = name.getText().toString();
                // get phone
                long phoneNum = Long.parseLong(contact.getText().toString());

                // handle Update
                String msg = User.currentUser.updateDetails(getApplicationContext(), nameStr, email.getText().toString(),phoneNum);
                Helpers.showToast(getApplicationContext(), msg);
            }catch (Exception e){
                // handle exception
                Helpers.showToast(getApplicationContext(), "Please Try Again Later");
            }
        });


    }


}