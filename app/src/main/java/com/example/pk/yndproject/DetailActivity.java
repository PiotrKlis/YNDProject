package com.example.pk.yndproject;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.yndproject.model.ImageItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import static com.example.pk.yndproject.R.id.progressBar;

/**
 * Created by PK on 17.08.2017.
 */

public class DetailActivity extends AppCompatActivity{

    private static final String IMAGE_KEY = "IMAGE";
    private static final String TAG = "DetailActivity";
    public ProgressBar progressBar;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView) findViewById(R.id.img_detail);
        TextView txtAuthor = (TextView) findViewById(R.id.txt_author);
        TextView txtImageSize = (TextView) findViewById(R.id.txt_img_size);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_detail);

        ImageItem imageItem = getIntent().getParcelableExtra(IMAGE_KEY);

        int width = imageItem.getWidth();
        int height = imageItem.getHeight();
        String author = imageItem.getAuthor();

        fullScreen();

       // AsyncTaskParams params = new AsyncTaskParams(width, height, author);
       // new DownloadAndDisplayImage().execute(params);

        String imgUrl =
                "https://unsplash.it/" + width + "/" + height + "?image=" + imageItem.getId();

        Picasso.with(getApplicationContext())
                .load(imgUrl)
                .error(R.drawable.error_icon)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "Picasso error");
                    }
                });


        txtAuthor.setText(imageItem.getAuthor());
        txtImageSize.setText(width + " x " + height);

    }

    public void fullScreen() {

        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
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

        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    private class DownloadAndDisplayImage extends AsyncTask<AsyncTaskParams, Void, String> {

        @Override
        protected String doInBackground(AsyncTaskParams... params) {

            int width = params[0].width;
            int height = params[0].height;
            String author = params[0].author;

            String imgUrl =
                    "https://unsplash.it/" + width + "/" + height + "?image=" + author;
            Picasso.with(getApplicationContext())
                    .load(imgUrl)
                    .error(R.drawable.error_icon)
                    .into(imageView);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private static class AsyncTaskParams {
        int width;
        int height;
        String author;

        AsyncTaskParams(int width, int height, String author) {
            this.width = width;
            this.height = height;
            this.author = author;
        }
    }

}
