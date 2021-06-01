package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class IMDB extends AppCompatActivity {

    ImageView img;
    TextView textView;
    TextView textView2;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_m_d_b);
        img = (ImageView)findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView32);
        textView2 = (TextView) findViewById(R.id.textView25);

        ArrayList<String> receivedData = (ArrayList<String>) getIntent().getSerializableExtra("ReceiveData");

        Log.d("Display data", String.valueOf(receivedData));
        String year = receivedData.get(0);
        String rating = receivedData.get(1);
        String name = receivedData.get(2);
        String url = receivedData.get(3);
        textView.setText(name);
        textView2.setText(rating);
        if (textView2.getText().toString().equals("")) {
            textView2.setTextColor(Color.RED);
            textView2.setTextSize(20);
            textView2.setText(R.string.noData);
        }
        new DownLoadImageTask(img).execute(url);

    }


    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView img;

        public DownLoadImageTask(ImageView imageView){
            this.img = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            img.setImageBitmap(result);
        }
    }
}