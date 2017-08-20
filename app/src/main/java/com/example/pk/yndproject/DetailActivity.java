package com.example.pk.yndproject;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pk.yndproject.model.ImageItem;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by PK on 17.08.2017.
 */

public class DetailActivity extends AppCompatActivity{

    private static final String IMAGE_KEY = "IMAGE";
    private static final String POSITION_KEY = "POSITION";
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ArrayList<ImageItem> imageItemArray = getIntent().getParcelableArrayListExtra(IMAGE_KEY);
        int position = getIntent().getIntExtra(POSITION_KEY, 0);

        fullScreen();

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), imageItemArray);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(position);
    }

    public void fullScreen() {

        int uiOptions = this.getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;

        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i(TAG, "Turning immersive mode mode off. ");
        } else {
            Log.i(TAG, "Turning immersive mode mode on.");
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        if (Build.VERSION.SDK_INT >= 19) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        this.getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        ArrayList<ImageItem> imageItems = new ArrayList<>();

        SectionsPagerAdapter(FragmentManager fragmentManager, ArrayList<ImageItem> imageItems) {
            super(fragmentManager);
            this.imageItems = imageItems;
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(
                    imageItems.get(position).getWidth(),
                    imageItems.get(position).getHeight(),
                    imageItems.get(position).getAuthor(),
                    imageItems.get(position).getId());
        }

        @Override
        public int getCount() {
            return imageItems.size();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        int id;
        int width;
        int height;
        String author;

        private static final String ARG_IMG_WIDTH = "image_width";
        private static final String ARG_IMAGE_HEIGHT = "image_height";
        private static final String ARG_AUTHOR = "image_author";
        private static final String ARG_ID = "image_id";

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            this.width = args.getInt(ARG_IMG_WIDTH);
            this.height = args.getInt(ARG_IMAGE_HEIGHT);
            this.author = args.getString(ARG_AUTHOR);
            this.id = args.getInt(ARG_ID);
        }

        public static PlaceholderFragment newInstance(int width, int height, String author, int id) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_IMG_WIDTH, width);
            args.putInt(ARG_IMAGE_HEIGHT, height);
            args.putString(ARG_AUTHOR, author);
            args.putInt(ARG_ID, id);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public void onStart() {
            super.onStart();

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_detail, container, false);

            ImageView imageView = (PhotoView) view.findViewById(R.id.img_detail);
            TextView txtAuthor = (TextView) view.findViewById(R.id.txt_author);
            TextView txtImageSize = (TextView) view.findViewById(R.id.txt_img_size);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar_detail);

            String imgUrl =
                "https://unsplash.it/" + width/4 + "/" + height/4 + "?image=" + id;

            Picasso.with(getContext())
                .load(imgUrl)
                .error(R.drawable.error_icon)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "Picasso error");
                    }
                });


            txtAuthor.setText(author);
            txtImageSize.setText(width + " x " + height);

            return view;
        }
    }
}
