package com.example.pk.yndproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by PK on 17.08.2017.
 */

public class DetailActivity extends AppCompatActivity{

    public static final String IMAGE_ELEMENT = "IMAGE_ELEMENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.img_detail);



    }
}
