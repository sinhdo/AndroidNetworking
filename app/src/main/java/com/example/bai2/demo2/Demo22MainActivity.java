package com.example.bai2.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai2.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo22MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo22_main);
        button=findViewById(R.id.demo22Btn2);
        textView=findViewById(R.id.demo22Tv2);
        imageView=findViewById(R.id.demo22ImageView2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goi AsyncTask
                new Demo22Asyn().execute("http://tinypic.com/images/goodbye.jpg");
            }
        });
    }
    class Demo22Asyn extends AsyncTask<String,Void, Bitmap> {
        //ham doc du lieu tu server
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //ham tra ket qua ve client
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap!=null){
                imageView.setImageBitmap(bitmap);
            }
            else {
                textView.setText("Tai anh loi");
            }
        }
    }
}