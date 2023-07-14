package com.example.bai2.demo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bai2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Demo31MainActivity extends AppCompatActivity {
    EditText txt1,txt2;
    Button btn1;
    TextView tvKQ;
    String path="https://batdongsanabc.000webhostapp.com/mob403/demo2_api_get.php";
    String pathPOST="https://batdongsanabc.000webhostapp.com/mob403/demo2_api_post.php";
    String kq="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo31_main);
        txt1 = findViewById(R.id.demo31Txt1);
        txt2 = findViewById(R.id.demo31Txt2);
        btn1 = findViewById(R.id.demo31Btn1);
        tvKQ=findViewById(R.id.demo31Tv1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new GETAsytask().execute();
                new POSTAsytask().execute();
            }
        });
    }
    class  GETAsytask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            path+="?name="+txt1.getText().toString()+"&mark="+txt2.getText().toString();

            try {
                URL url =new URL(path);
                //tạo bộ đếm để dọc dữ liệu
                BufferedReader br=new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                //bắt đầu đọc
                String line = ""; //biến lưu từng dòng dữ liệu
                StringBuilder stringBuilder = new StringBuilder();//chứa toàn bộ nội dung đọc được từ while
                while ((line=br.readLine())!=null){
                    stringBuilder.append(line);
                    kq=stringBuilder.toString();//tra ve ket qua
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            tvKQ.setText(kq);
        }
    }

    class  POSTAsytask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //chuyển đường dẫn thành url
                URL url = new URL(pathPOST);
                //2. xử lý tham số post
                String param="canh="+ URLEncoder.encode(txt1.getText().toString(),"utf-8");
                //3. mở kết nối
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //4.set thuộc tính cho tham số posst
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");//xác định phương thức
                urlConnection.setFixedLengthStreamingMode(param.getBytes().length);//độ dài dữ liệu
                //5
                urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                //6. doc du lieu
                // 6.1 lay ve tham so
                PrintWriter printWriter=new PrintWriter(urlConnection.getOutputStream());
                printWriter.print(param);
                printWriter.close();
                //6.2 tien hanh doc du lieu
                String line ="";
                BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder=new StringBuilder();
                while ((line=br.readLine())!=null) {
                    stringBuilder.append(line);
                }
                //6.3 tra ve ket qua
                 kq=stringBuilder.toString();
                //7. dong ket noi
                urlConnection.disconnect();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            tvKQ.setText(kq);
        }
    }


}