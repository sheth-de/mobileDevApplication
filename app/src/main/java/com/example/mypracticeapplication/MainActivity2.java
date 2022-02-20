package com.example.mypracticeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button A;
    Button B;
    Button C;
    Button D;
    Button E;
    Button F;
    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        message = findViewById(R.id.contentToChange);
        A = findViewById(R.id.buttonA);
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Pressed: A");
            }
        });
        B = findViewById(R.id.buttonB);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Pressed: B");
            }
        });
        C = findViewById(R.id.buttonC);
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Pressed: C");
            }
        });
        D = findViewById(R.id.buttonD);
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Pressed: D");
            }
        });
        E = findViewById(R.id.buttonE);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Pressed: E");
            }
        });
        F = findViewById(R.id.buttonF);
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText("Pressed: F");
            }
        });
    }
}