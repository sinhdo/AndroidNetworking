package com.example.bai2.demo2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Demo21AsyncTask extends AsyncTask<String,Void, Bitmap> {
    private Demo21Interface anInterface;//lang nghe va tra ket qua
    private Context context;
    public Demo21AsyncTask(Demo21Interface anInterface, Context context) {
        this.anInterface = anInterface;
        this.context=context;
    }
    //ham doc du lieu tu server
    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());

        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
    //tra ket qua ve client
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null)//neu ton tai anh
        {
            anInterface.onLoadAnh(bitmap);//tra anh ve client
        }
        else {
            anInterface.onLoi();
        }
    }
}
