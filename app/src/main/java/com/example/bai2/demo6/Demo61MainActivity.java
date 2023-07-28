package com.example.bai2.demo6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bai2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Demo61MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    TextView tvKQ;
    Context context = this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo61_main);
        btn1 = findViewById(R.id.demo61Btn1);
        btn2 = findViewById(R.id.demo61Btn2);
        tvKQ = findViewById(R.id.demo61TvKQ);
        btn1.setOnClickListener(view -> {
            getStringByVolley();
        });
        btn2.setOnClickListener(view -> {
            getJSON_ObjectOfArray();
        });
    }
    String strKQ ="";

    private void getJSON_ObjectOfArray() {
        //tao hang đơn
        RequestQueue queue = Volley.newRequestQueue(context);
        //truyen url
        String url = "https://batdongsanabc.000webhostapp.com/mob403lab6/array_json_new.json";
        //tạo request
        //truong hop nay là arrayrequest
        //JsonArrayRequest(url,thanh con, that bai)
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //ket qua tra ve mang cua cac doi tuong
                // can dùng for để đọc hết các đối tượng
                strKQ = "";
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject person = response.getJSONObject(i); //lấy đối tượng i
                        String id = person.getString("id");
                        String name = person.getString("name");
                        String email = person.getString("email");
                        JSONObject phone = person.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        // nối chuỗi
                        strKQ+="=============="+"\n\n";
                        strKQ+="id: "+id+"\n\n";
                        strKQ+="name: "+name+"\n\n";
                        strKQ+="emai: "+email+"\n\n";
                        strKQ+="mobile: "+mobile+"\n\n";
                        strKQ+="home "+home+"\n\n";
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                tvKQ.setText(strKQ);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        });
        // truyen tham so
        //xu ly request
        queue.add(request);

    }

    private void getStringByVolley() {
        // b1 tạo request
        RequestQueue queue = Volley.newRequestQueue(context);
        //b2 truyen url
        String url = "https://www.google.com/";
        //b3 truyen tham so
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText("Kết quá : " + response.substring(0, 1000));
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    tvKQ.setText(error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
}