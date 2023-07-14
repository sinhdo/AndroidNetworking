package com.example.bai2.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai2.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo23MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo23_main);
        button=findViewById(R.id.demo23Btn);
        textView=findViewById(R.id.demo23Tv);
        imageView=findViewById(R.id.demo23ImageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread myThread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //doc anh
                        Bitmap bitmap=loadAnh("http://tinypic.com/images/goodbye.jpg");
                        //dua anh vao imageview
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                textView.setText("Thanh cong");
                            }
                        });
                    }
                });
                myThread.start();
            }
        });
    }
    //dinh nghia ham load anh
    private Bitmap loadAnh(String str){
        URL url;
        Bitmap bitmap = null;
        try {
            url=new URL(str);
            bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}