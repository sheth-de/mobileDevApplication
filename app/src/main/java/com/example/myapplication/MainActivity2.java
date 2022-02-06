package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button A;
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
    }
}