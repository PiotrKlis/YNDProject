package com.example.pk.yndproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pk.yndproject.DetailActivity;
import com.example.pk.yndproject.R;
import com.example.pk.yndproject.model.ImageItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PK on 18.08.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageHolder> {
    private static ArrayList<String> authorArray = new ArrayList<>();
    private static ArrayList<ImageItem> mImages;
    private static String TAG = "RecyclerAdapter";

    public RecyclerAdapter(ArrayList<ImageItem> images) {
        mImages = images;
    }

    @Override
    public RecyclerAdapter.ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new ImageHolder(inflatedView);
    }


    @Override
    public void onBindViewHolder(RecyclerAdapter.ImageHolder holder, int position) {

        ImageItem imageItem = mImages.get(position);
        holder.mImgView.setVisibility(View.GONE);
        holder.mProgressBar.setVisibility(View.VISIBLE);
        holder.bindPhoto(imageItem);

    }

    @Override
    public int getItemCount() {
       return mImages.size();
    }

     static class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImgView;
        private TextView mTxtView;
        private ProgressBar mProgressBar;

         ImageHolder(View v) {
            super(v);

            mImgView = (ImageView) v.findViewById(R.id.img_recycler);
            mTxtView = (TextView) v.findViewById(R.id.txt_recycler);
            mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);

            v.setOnClickListener(this);
        }

        private static final String IMAGE_KEY = "IMAGE";
        private static final String POSITION_KEY = "POSITION";

        @Override
        public void onClick(View view) {
            Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, DetailActivity.class);
            showPhotoIntent.putParcelableArrayListExtra(IMAGE_KEY, mImages);
            showPhotoIntent.putExtra(POSITION_KEY, getAdapterPosition());
            context.startActivity(showPhotoIntent);
        }

        void bindPhoto(ImageItem imageItem) {

            String imgUrl = "https://unsplash.it/100/50?image=" + imageItem.getId();
            Picasso.with(mImgView.getContext())
                    .load(imgUrl)
                    .error(R.drawable.error_icon)
                    .into(mImgView, new Callback() {
                        @Override
                        public void onSuccess() {
                            mProgressBar.setVisibility(View.GONE);
                            mImgView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError() {
                            mProgressBar.setVisibility(View.GONE);
                            Log.i("RecyclerAdapter", "Error in bindPhoto method");
                        }
                    });

            String author = imageItem.getAuthor();

            // Populate the array with authors

            authorArray.add(author);

            // Check if there's number inside the string
            // if not, use setter to update the data
            // add space and number of frequency of given author in an array

            if (!author.matches(".*\\d+.*")) {
                imageItem.setAuthor(author + " " + Collections.frequency(authorArray, author));
            }

            mTxtView.setText(imageItem.getAuthor());
        }
    }
}