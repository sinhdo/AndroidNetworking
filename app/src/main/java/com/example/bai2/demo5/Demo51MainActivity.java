package com.example.bai2.demo5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bai2.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo51MainActivity extends AppCompatActivity {
    EditText txt1,txt2,txt3,txt4;
    Button btn1,btn2,btn3;
    TextView tvKQ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo51_main);
        txt1=findViewById(R.id.demo51Txt1);
        txt2=findViewById(R.id.demo51Txt2);
        txt3=findViewById(R.id.demo51Txt3);
        txt4=findViewById(R.id.demo51Txt4);
        btn1=findViewById(R.id.demo51Btn1);
        btn2=findViewById(R.id.demo51Btn2);
        btn3=findViewById(R.id.demo51Btn3);
        tvKQ = findViewById(R.id.demo51TvKQ);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRestrofit();
            }


        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRestrofit();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRestrofit();
            }
        });
    }

    ArrayList<Prod> ls;
    String strKQ = "";
    private void selectRestrofit() {
        //B0. Chuan bi du lieu
        //b1. Tao doi tuong Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterSelect interSelect=retrofit.create(InterSelect.class);
        Call<SvrResponseSelect> call=interSelect.selectDB();
        call.enqueue(new Callback<SvrResponseSelect>() {
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                //lay ve key qua
                SvrResponseSelect svrResponseSelect=response.body();
                //chuyen mang sang list
                ls=new ArrayList<>(Arrays.asList(svrResponseSelect.getProducts()));
                //for
                strKQ="";
                for(Prod p: ls){
                    strKQ += "Pid: "+p.getPid()
                            +" - "+p.getName()
                            +" - "+p.getPrice()
                            +" - "+p.getDescription()+"\n";
                }
                //dua ket qua len man hinh
                tvKQ.setText(strKQ);
            }

            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable t) {
                tvKQ.setText(t.getMessage());//dua ra thong bao loi
            }
        });
    }
    private void updateRestrofit() {
        //b0, chuẩn bị dữ liệu
        Prod p =new Prod();
        p.setPid(txt1.getText().toString());
        p.setName(txt2.getText().toString());
        p.setPrice(txt3.getText().toString());
        p.setDescription(txt4.getText().toString());
        //b1. tạo đối tượng restrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //b2. goi interface +chuản bị hàm + thực thi hàm
        InterUpdate interUpdate = retrofit.create(InterUpdate.class);
        Call<SvrResponseUpdate> call = interUpdate.updateDB(p.getPid(),
                p.getName(),p.getPrice(),p.getDescription());
        call.enqueue(new Callback<SvrResponseUpdate>() {
            @Override
            public void onResponse(Call<SvrResponseUpdate> call, Response<SvrResponseUpdate> response) {
                SvrResponseUpdate svrResponUpdate = response.body();
                tvKQ.setText(svrResponUpdate.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseUpdate> call, Throwable t) {
            tvKQ.setText(t.getMessage());
            }
        });
    }
    private void deleteRestrofit() {
        //b0, chuẩn bị dữ liệu
        Prod p =new Prod();
        p.setPid(txt1.getText().toString());
        //b1. tạo đối tượng restrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. goi interface +chuản bị hàm + thực thi hàm
        InterDelete interDelete = retrofit.create(InterDelete.class);
        Call<SvrResponseDelete> call = interDelete.deleteDB(p.getPid());
        call.enqueue(new Callback<SvrResponseDelete>() {
            @Override
            public void onResponse(Call<SvrResponseDelete> call, Response<SvrResponseDelete> response) {
                SvrResponseDelete svrResponDelete = response.body();
                tvKQ.setText(svrResponDelete.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseDelete> call, Throwable t) {
                    tvKQ.setText(t.getMessage());
            }
        });
    }
}