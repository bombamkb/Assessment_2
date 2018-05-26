package com.khamban.assessment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khamban.Adapter.Adapt_Makeassess;
import com.khamban.Adapter.Adapt_login;
import com.khamban.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login_complete extends AppCompatActivity {
    private RecyclerView recyclerview;
    String User,Pass;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_complete);
        recyclerview = (RecyclerView) findViewById(R.id.Recy_login);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Login_complete.this);
        recyclerview.setLayoutManager(layoutManager);
        User = getIntent().getStringExtra("Username");
        Pass = getIntent().getStringExtra("Password");

        getquesstion1(User,Pass);
    }

    public void getquesstion1(String user,String pass) {
        Toast.makeText(Login_complete.this, "Login Success", Toast.LENGTH_SHORT).show();
        String url = "http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/validate_login.php?user="+user+"&pass="+pass;

//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

//        final int finalIndex;
//        Toast.makeText(Login_complete.this, ""+index, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result =  jsonObject.getJSONArray("User_login");
//                    Toast.makeText(getActivity(), "เข้าฟังก์ชัน >> "+result, Toast.LENGTH_SHORT).show();
                    String Name = "",Pic="";
                    String[] data = new String[result.length()];
                    String[] data1 = new String[result.length()];
//                    ImageView[] img = new ImageView[result.length()];
                    for (int i = 0 ; i < result.length();i++){

                        JSONObject collectData = result.getJSONObject(i);
                        Name = collectData.getString("Fullname");
//                        JSONObject collectData1 = result.getJSONObject(i);
                        Pic = collectData.getString("pic");

                        data[i] = Name;
                        data1[i] = Pic;

//                        Toast.makeText(Login_complete.this, "URL รูป : "+data1[i], Toast.LENGTH_SHORT).show();
                    }
//                    Toast.makeText(Login_complete.this, "URL รูป : "+data1[i], Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "onResponse: "+data.length);
                    Adapt_login adapter = new Adapt_login(data,data1,Login_complete.this);
                    recyclerview.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(getActivity(), "เข้าลูป", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), list.get(0), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        );
//
        RequestQueue requestQueue = Volley.newRequestQueue(Login_complete.this);
        requestQueue.add(stringRequest);
    }

    public void login(View view){
        Intent home = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(home);
    }
}
