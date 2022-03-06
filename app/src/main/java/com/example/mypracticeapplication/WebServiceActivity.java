package com.example.mypracticeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class WebServiceActivity extends AppCompatActivity {

    private static final String TAG = "WebServiceActivity";
    private EditText animeName;
    private Button search;
    private RecyclerView recyclerView;
    private TextView buffer;
    private ArrayList<String> anime_name_list = new ArrayList<>();
    private ArrayList<String> rating_list = new ArrayList<>();
    private ArrayList<String> image_list = new ArrayList<>();
    private ProgressBar progressBarAnime;
    private JSONObject jObject = new JSONObject();
    private Handler jsonHandler = new Handler();

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        recyclerView = (RecyclerView) findViewById(R.id.web_services_result);
        if (savedInstanceState != null) {
            int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);
            for (int i = 0; i < size; i++) {
                String anime_name = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                anime_name_list.add(anime_name);
                String anime_rating = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                rating_list.add(anime_rating);
                String image_new = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");
                image_list.add(image_new);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            AnimeViewAdapter adapter = new AnimeViewAdapter(WebServiceActivity.this, rating_list, anime_name_list, image_list);
            recyclerView.setAdapter(adapter);
        }
        progressBarAnime = findViewById(R.id.progressBarAnime);
        animeName = findViewById(R.id.anime_name_edittext);
        buffer = findViewById(R.id.buffer_holder);
        search = findViewById(R.id.search_btn);
        progressBarAnime.setVisibility(View.INVISIBLE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarAnime.setVisibility(View.VISIBLE);
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        hideKeybaord(view);
                        sendRequest();
                        jsonHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("Vheck thread","post");
                                postRunUIChange();
                            }
                        });
                    }
                });
                thread1.start();
            }
        });
    }

    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }

    private void sendRequest() {
        String anime = animeName.getText().toString();
        String link1 = "https://api.jikan.moe/v4/anime?q=";
        String link2 = "&sfw";
        String link = link1 + anime + link2;
        try {
            URL url = new URL(link);
            Log.i("Imp check", url.toString());
            String resp = NetworkUtil.httpResponse(url);
            Log.i("Imp check", resp);
            jObject = new JSONObject(resp);
//                            return jObject;
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException");
            e.printStackTrace();
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "IOException");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(TAG, "JSONException");
            e.printStackTrace();
        }
    }

    private void postRunUIChange() {

        progressBarAnime.setVisibility(View.INVISIBLE);
        JSONArray data = new JSONArray();
        if (anime_name_list.size() > 0) {
            anime_name_list.clear();
        }
        if (rating_list.size() > 0) {
            rating_list.clear();
        }
        if (image_list.size() > 0) {
            image_list.clear();
        }
        try {
            data = jObject.getJSONArray("data");
//                Log.i(TAG,String.valueOf(data.length()));
        } catch (JSONException e) {
            e.printStackTrace();
            buffer.setText("Something went wrong!");
        }
        if (data.length() <= 0) {
            buffer.setText("No Results found for your search!");
            anime_name_list.clear();
            rating_list.clear();
            image_list.clear();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            AnimeViewAdapter adapter = new AnimeViewAdapter(WebServiceActivity.this, rating_list, anime_name_list, image_list);
            recyclerView.setAdapter(adapter);
        } else {
            buffer.setText("");
            for (int i = 0; i < data.length(); i++) {
                try {
                    anime_name_list.add(data.getJSONObject(i).getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    rating_list.add(data.getJSONObject(i).getString("score"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    String imageUrlNew = data.getJSONObject(i).getJSONObject("images").getJSONObject("jpg").getString("image_url");
                    image_list.add(imageUrlNew);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            AnimeViewAdapter adapter = new AnimeViewAdapter(WebServiceActivity.this, rating_list, anime_name_list, image_list);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = anime_name_list == null ? 0 : anime_name_list.size();
        outState.putInt(NUMBER_OF_ITEMS, size);
        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_INSTANCE + i + "0", anime_name_list.get(i));
            outState.putString(KEY_OF_INSTANCE + i + "1", rating_list.get(i));
            outState.putString(KEY_OF_INSTANCE + i + "2", image_list.get(i));
        }
        super.onSaveInstanceState(outState);
    }

    class differentThread extends Thread{

    }

}