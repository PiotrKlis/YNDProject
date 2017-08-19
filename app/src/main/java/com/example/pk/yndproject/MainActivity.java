package com.example.pk.yndproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.pk.yndproject.adapter.RecyclerAdapter;
import com.example.pk.yndproject.http.RetrofitInterface;
import com.example.pk.yndproject.model.ImageItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  //      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                RetrofitInterface jsonService = RetrofitInterface.retrofit.create(RetrofitInterface.class);
                Call<ArrayList<ImageItem>> call = jsonService.getImageList();
                //ArrayList<ImageItem> imageItemArrayList = new ArrayList<>();


                call.enqueue(new Callback<ArrayList<ImageItem>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ImageItem>> call, Response<ArrayList<ImageItem>> response) {
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerAdapter = new RecyclerAdapter(response.body());
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ImageItem>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Wystąpił problem z połączeniem", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", call.request().toString());
                    }
                });
            }
        });
    }
}
