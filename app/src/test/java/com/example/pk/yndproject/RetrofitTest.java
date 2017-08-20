package com.example.pk.yndproject;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.pk.yndproject.adapter.RecyclerAdapter;
import com.example.pk.yndproject.http.RetrofitInterface;
import com.example.pk.yndproject.model.ImageItem;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RetrofitTest {

    @Test
    public void isDataResponseFromApi() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                RetrofitInterface jsonService = RetrofitInterface.retrofit.create(RetrofitInterface.class);
                Call<ArrayList<ImageItem>> call = jsonService.getImageList();

                call.enqueue(new Callback<ArrayList<ImageItem>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ImageItem>> call, Response<ArrayList<ImageItem>> response) {
                        Assert.assertNotNull(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ImageItem>> call, Throwable t) {

                    }
                });
            }
        });

    }
}