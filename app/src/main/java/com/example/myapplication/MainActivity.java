package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showToastBtn = findViewById(R.id.showtoastbtn);
        showToastBtn.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Devanshi Samir Sheth \n sheth.de@northeastern.edu", Toast.LENGTH_LONG).show());
    }
}