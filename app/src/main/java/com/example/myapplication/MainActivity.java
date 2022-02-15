package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button linkCollector;
    List<LinkItem> listOfLinks = new ArrayList<LinkItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button aboutMe = findViewById(R.id.aboutMe);
        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutMePage();
            }
        });
//        showToastBtn.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Devanshi Samir Sheth \n sheth.de@northeastern.edu", Toast.LENGTH_LONG).show());

        Button clickyClicky = findViewById(R.id.clicky);
        clickyClicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openClickyActivity();
            }
        });

        linkCollector = findViewById(R.id.linkCollector);
        linkCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlinkCollectorActivity();
            }
        });
    }

    private void openAboutMePage() {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

    public  void openClickyActivity(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public  void openlinkCollectorActivity(){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}