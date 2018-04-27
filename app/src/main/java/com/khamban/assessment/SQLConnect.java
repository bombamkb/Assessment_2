package com.khamban.assessment;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SQLConnect {

    private final Activity main;
    private List<String> list;
    private String URL ="http://10.0.2.2/",GET_URL ="android/get_post.php?status=0";

//    public SQLConnect(){
//        main = null;
//    }
    public SQLConnect(Activity mainA){
        main = mainA;
        list = new ArrayList<String>();

    }
    public List<String> getAssessment(){
        Toast.makeText(main, "เข้าฟั" +
                "ก์ชันนะ", Toast.LENGTH_LONG).show();
        String url = URL + GET_URL;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showAssessment(response);
                Toast.makeText(main, "เข้าลูปนะ", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);
        return list;
    }
    public void showAssessment(String response){

        String evaName ="";

        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result =  jsonObject.getJSONArray("result");

            for (int i = 0 ; i < result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                evaName = collectData.getString("name");
                list.add(evaName);
            }
        }catch (JSONException ex){ex.printStackTrace();}

    }

}
