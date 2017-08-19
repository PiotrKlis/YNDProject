package com.example.pk.yndproject.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.pk.yndproject.R;
import com.example.pk.yndproject.model.ImageItem;

import java.util.ArrayList;

/**
 * Created by PK on 19.08.2017.
 */

public class CustomPagerAdapter extends PagerAdapter{

    Context mContext;
    ArrayList<ImageItem> mImageItem;
    LayoutInflater mLayoutInflater;

    CustomPagerAdapter(Context context, ArrayList<ImageItem> imageItem) {
        mContext = context;
        mImageItem = imageItem;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mImageItem.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    /*@Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.recyclerview_item, container, false);

    }*/
}
