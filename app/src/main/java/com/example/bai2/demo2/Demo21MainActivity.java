package com.example.bai2.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai2.R;

public class Demo21MainActivity extends AppCompatActivity implements View.OnClickListener,Demo21Interface{

    Button button;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo21_main);
        button=findViewById(R.id.demo21Btn);
        textView=findViewById(R.id.demo21Tv);
        imageView=findViewById(R.id.demo21ImageView);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //khi click button thi goi asynctask
        new Demo21AsyncTask(this,this)
                .execute("http://tinypic.com/images/goodbye.jpg");
    }

    @Override
    public void onLoadAnh(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onLoi() {
        textView.setText("loi doc du lieu");
    }
}