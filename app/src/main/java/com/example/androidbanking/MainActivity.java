package com.example.androidbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE}, 1);

    }
}