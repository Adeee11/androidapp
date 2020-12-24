package com.example.androidbanking;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Signuppage extends AppCompatActivity {

    EditText name,phone;
    Button signUpBtn, backBtn;
    RadioButton savings, current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        // binding variables to screen elements
        name = findViewById(R.id.name);
        phone = findViewById(R.id.contact);
        savings = findViewById(R.id.savingsradio);
        current = findViewById(R.id.currentradio);
        signUpBtn = findViewById(R.id.signupbtn);
        backBtn = findViewById(R.id.backtologin);

        // initialzed saving radio as checked
        savings.setChecked(true);
        // handle radio click
        savings.setOnClickListener((View v) -> {
            savings.setChecked(true);
            current.setChecked(false);
        });
        // handle radio click
        current.setOnClickListener((View v) -> {
            savings.setChecked(false);
            current.setChecked(true);
        });
        signUpBtn.setOnClickListener((View v)-> {
            // get custname
            String custName = name.getText().toString();
            if(custName.length() < 2){
                 Helpers.showToast(getApplicationContext(),"Please Enter A Valid Customer Name");
                 return;
            }
            // get phone num
            String text = phone.getText().toString();
            if(text.length() < 10){
                Helpers.showToast(getApplicationContext(),"Please Enter A Valid 10 digit Phone Number");
                return;
            }
            long phoneNum = Long.parseLong(text);
            // get account type
            String type = savings.isChecked()? "Savings": "Current";
            // handle signup
            User user =  User.signUp(getApplicationContext(),custName,phoneNum,type);
            // show account details in a dialog
            new AlertDialog.Builder(this)
                    .setTitle("Account Details")
                    .setMessage("Your Account Has been created Successfully. Your Access Number is "+
                            user.uid + " and Pin is "+ user.password +". Please Write Them Somewhere")
                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            finish();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_secure).create()
                    .show();
        });

        // handle back button click
        backBtn.setOnClickListener((View v)-> {
            finish();
        });


    }

}