package com.khamban.assessment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khamban.Adapter.Adapt_Subject_andTeacher;
import com.khamban.Adapter.Adapt_before;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Report_before extends Fragment {
    RecyclerView recyclerview;
    int index;

    public Report_before(int index) {
        this.index = index;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_before, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.Recy_Subject_report);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        getquesstion1(index);

        return view;
    }
    public void getquesstion1(int index) {
//        Toast.makeText(Login_complete.this, "User : "+user+"Pass : "+pass, Toast.LENGTH_SHORT).show();
        String url = "http://10.80.39.17/TSP58/nursing/index.php/amis/Mobile/Ionic/Service/showAssessTeach_android_com/"+index;

//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

//        final int finalIndex;
//        Toast.makeText(Login_complete.this, ""+index, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result =  jsonObject.getJSONArray("Subject_teacher");
//                    Toast.makeText(getActivity(), "เข้าฟังก์ชัน >> "+result, Toast.LENGTH_SHORT).show();
                    String Name_sub = "",Name_teach="",status_teach="",Evu_id="";
                    String[] data = new String[result.length()];
                    String[] data1 = new String[result.length()];
                    String[] data2 = new String[result.length()];
                    String[] data3 = new String[result.length()];
//                    ImageView[] img = new ImageView[result.length()];
                    for (int i = 0 ; i < result.length();i++){

                        JSONObject collectData = result.getJSONObject(i);
                        Name_sub = collectData.getString("Subject_name");
//                        JSONObject collectData1 = result.getJSONObject(i);
                        Name_teach = collectData.getString("fullname");
                        status_teach = collectData.getString("Teaacher_status");
                        Evu_id = collectData.getString("Evu_id");

                        if(status_teach.equals("0")){
                            status_teach = "ยังไม่ประเมิน";
                        }else {
                            status_teach = "ดูรายละเอียด";
                        }
                        data[i] = Name_sub;
                        data1[i] = Name_teach;
                        data2[i] = status_teach;
                        data3[i] = Evu_id;

//                        Toast.makeText(getActivity(), "URL รูป : "+data1[i], Toast.LENGTH_SHORT).show();
                    }

                    Log.d("onResponse", "onResponse: "+data.length);
                    Adapt_before adapter = new Adapt_before(data,data1,data2,data3);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
