package com.example.pk.yndproject.http;

import com.example.pk.yndproject.model.ImageItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by PK on 17.08.2017.
 */

public interface RetrofitInterface {

    String apiBaseURL = "https://unsplash.it/";

    @GET("list")
    Call<ArrayList<ImageItem>> getImageList();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(apiBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
