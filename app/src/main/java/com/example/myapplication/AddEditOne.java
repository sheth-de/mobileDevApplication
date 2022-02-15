package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEditOne extends AppCompatActivity {

    Button btn_save;
    Button btn_cancel;
    List<LinkItem> listOfLinks;
    MyApplication myApplication = (MyApplication) this.getApplication();
    EditText name, url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_one);
        listOfLinks = myApplication.getItemList();
        btn_save = findViewById(R.id.btn_save_link);
        name = findViewById(R.id.input_name);
        url = findViewById(R.id.input_user_url);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeybaord(view);
                if(isValidUrl(url.getText().toString())){
                    LinkItem linkItem = new LinkItem(name.getText().toString(),url.getText().toString());
                    listOfLinks.add(linkItem);
                    Snackbar mySnackbar = Snackbar.make(view, "Link Created Successfully", Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            Intent intent1 = new Intent(AddEditOne.this,MainActivity3.class);
                            startActivity(intent1);
                        }
                    }, 1000);
                }
               else {
                    Snackbar mySnackbar = Snackbar.make(view, "Link is not Valid", Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                }
            }
        });

        btn_cancel = findViewById(R.id.btn_cancel_link);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AddEditOne.this, MainActivity3.class);
                startActivity(intent2);
            }
        });
    }
    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
    private boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        return m.matches();
    }
}