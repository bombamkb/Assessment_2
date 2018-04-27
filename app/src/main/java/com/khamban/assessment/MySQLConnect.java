package com.khamban.assessment;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khamban.Adapter.Adapt_Assessment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MySQLConnect {
    private final Activity main;
    private List<String> list,list2,list_questtion1_term1,list_questtion1_term2;
    private String URL ="http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php";
    private RecyclerView recyclerview;


//    public MySQLConnect(Activity main) {
//        this.main = main;
//    }

        public MySQLConnect(){
        this.main = null;
    }
    public MySQLConnect(Activity mainA){
        this.main = mainA;
        this.list = new ArrayList<String>();
        this.list2 = new ArrayList<String>();

    }
//    Function getAssessment For แบบประเมินที่ยังไม่ทำการประเมิน
    public List<String> getAssessment(){
        String url = URL;
        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

       StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               showAssessment(response);
               Toast.makeText(main, "เข้าลูป", Toast.LENGTH_SHORT).show();
               Toast.makeText(main, list.get(0), Toast.LENGTH_SHORT).show();

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(main, error.getMessage().toString(),Toast.LENGTH_SHORT).show();
           }
       }

       );
//
       RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
       requestQueue.add(stringRequest);
        return list;
    }
    public void showAssessment(String response){
        Toast.makeText(main, "เข้าลูป 2", Toast.LENGTH_SHORT).show();
        String evaName ="";

        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result =  jsonObject.getJSONArray("Assessment");

            for (int i = 0 ; i < result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                evaName = collectData.getString("name");
                list.add(evaName);
            }

        }catch (JSONException ex){ex.printStackTrace();}

    }

    //    Function getReport For แบบประเมินที่ประเมินแล้ว
    public List<String> getReport(){
        String url = URL;
//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showReport(response);
//               Toast.makeText(main, "เข้าลูป", Toast.LENGTH_SHORT).show();
                Toast.makeText(main, list2.get(0), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);
        return list2;
    }
    public void showReport(String response){
//        Toast.makeText(main, "เข้าลูป 2", Toast.LENGTH_SHORT).show();
        String evaName ="";

        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result =  jsonObject.getJSONArray("Topic_term1");

            for (int i = 0 ; i < result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                evaName = collectData.getString("Question");
                list2.add(evaName);
            }

        }catch (JSONException ex){ex.printStackTrace();}

    }

    //    Function getquesstion1 For ข้อคำถามเทอม1
    public List<String> getquesstion1(){
        String url = URL;
//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showQuesstion1(response);
//               Toast.makeText(main, "เข้าลูป", Toast.LENGTH_SHORT).show();
                Toast.makeText(main, list2.get(0), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);
        return list_questtion1_term1;
    }
    public void showQuesstion1(String response){
//        Toast.makeText(main, "เข้าลูป 2", Toast.LENGTH_SHORT).show();
        String Question,Sub_Question ="";

        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result =  jsonObject.getJSONArray("Topic_term1");
            JSONArray result_1 = jsonObject.getJSONArray("Ruestion_sub1_term1");
            JSONArray result_2 = jsonObject.getJSONArray("Ruestion_sub2_term1");
            JSONArray result_3 = jsonObject.getJSONArray("Ruestion_sub3_term1");
            JSONArray result_4 = jsonObject.getJSONArray("Ruestion_sub4_term1");

            for (int i = 0 ; i < result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                Question = collectData.getString("Question");
                list_questtion1_term1.add(Question);
                if(i==0) {
                    for (int j = 0; j < result_1.length(); j++) {
                        JSONObject collectData_1 = result_1.getJSONObject(j);
                        Sub_Question = collectData_1.getString("Sub_1_name");
                        list_questtion1_term1.add(Sub_Question);
                    }
                }
                if(i==1){
                    for (int j = 0; j < result_1.length(); j++) {
                        JSONObject collectData_1 = result_2.getJSONObject(j);
                        Sub_Question = collectData_1.getString("Sub_2_name");
                        list_questtion1_term1.add(Sub_Question);
                    }
                }
                if(i==2){
                    for (int j = 0; j < result_1.length(); j++) {
                        JSONObject collectData_1 = result_3.getJSONObject(j);
                        Sub_Question = collectData_1.getString("Sub_3_name");
                        list_questtion1_term1.add(Sub_Question);
                    }
                }
                if(i==3){
                    for (int j = 0; j < result_1.length(); j++) {
                        JSONObject collectData_1 = result_4.getJSONObject(j);
                        Sub_Question = collectData_1.getString("Sub_4_name");
                        list_questtion1_term1.add(Sub_Question);
                    }
                }
            }

        }catch (JSONException ex){ex.printStackTrace();}

    }
    //    Function getquesstion2 For ข้อคำถามเทอม2
    public List<String> getquesstion2(){
        String url = URL;
//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showQuesstion2(response);
//               Toast.makeText(main, "เข้าลูป", Toast.LENGTH_SHORT).show();
                Toast.makeText(main, list2.get(0), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);
        return list_questtion1_term2;
    }
    public void showQuesstion2(String response){
//        Toast.makeText(main, "เข้าลูป 2", Toast.LENGTH_SHORT).show();
        String Question,Sub_Question ="";

        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result =  jsonObject.getJSONArray("Topic_term2");
            JSONArray result_1 = jsonObject.getJSONArray("Ruestion_sub1_term2");
            JSONArray result_2 = jsonObject.getJSONArray("Ruestion_sub2_term2");
            JSONArray result_3 = jsonObject.getJSONArray("Ruestion_sub3_term2");

            for (int i = 0 ; i < result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                Question = collectData.getString("Question");
                list_questtion1_term2.add(Question);
                if(i==0) {
                    for (int j = 0; j < result_1.length(); j++) {
                        JSONObject collectData_1 = result_1.getJSONObject(j);
                        Sub_Question = collectData_1.getString("Sub_1_name");
                        list_questtion1_term2.add(Sub_Question);
                    }
                }
                if(i==1){
                    for (int j = 0; j < result_1.length(); j++) {
                        JSONObject collectData_1 = result_2.getJSONObject(j);
                        Sub_Question = collectData_1.getString("Sub_2_name");
                        list_questtion1_term2.add(Sub_Question);
                    }
                }
                if(i==2){
                    for (int j = 0; j < result_1.length(); j++) {
                        JSONObject collectData_1 = result_3.getJSONObject(j);
                        Sub_Question = collectData_1.getString("Sub_3_name");
                        list_questtion1_term2.add(Sub_Question);
                    }
                }
            }

        }catch (JSONException ex){ex.printStackTrace();}

    }
}
