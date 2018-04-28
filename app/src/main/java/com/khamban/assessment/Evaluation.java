package com.khamban.assessment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khamban.Adapter.Adapt_Assessment;
import com.khamban.Adapter.Adapt_Makeassess;
import com.khamban.Adapter.Adapt_Makeassess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Evaluation extends Fragment {
    private List<String> items,list;
    private String URL ="http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php";
    private RecyclerView recyclerview;


    public Evaluation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.Recy_doing);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        items = getquesstion1();
        return  view;
    }
    public List<String> getquesstion1(){
        String url = URL;
//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                showQuesstion1(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result =  jsonObject.getJSONArray("Topic_term1");
                    JSONArray result_1 = jsonObject.getJSONArray("Ruestion_sub1_term1");
                    JSONArray result_2 = jsonObject.getJSONArray("Ruestion_sub2_term1");
                    JSONArray result_3 = jsonObject.getJSONArray("Ruestion_sub3_term1");
                    JSONArray result_4 = jsonObject.getJSONArray("Ruestion_sub4_term1");
                    String[] list_questtion1_term1 = new String[result.length()];
                    String Question,Sub_Question;
//                    String[] Sub_Question = new String[result_1.length()];
                    for (int i = 0 ; i < result.length();i++){
                        JSONObject collectData = result.getJSONObject(i);
                        Question = collectData.getString("Question");
                        list_questtion1_term1[i]=Question;
                        if(i==0) {
                            list_questtion1_term1 = new String[result.length()+result_1.length()];
                            for (int j = 0; j < result_1.length(); j++) {
                                JSONObject collectData_1 = result_1.getJSONObject(j);
                                Sub_Question = collectData_1.getString("Sub_1_name");
                                list_questtion1_term1[j] = Sub_Question;
                            }
                        }
                        if(i==1){
                            list_questtion1_term1 = new String[result.length()+result_2.length()];
                            for (int j = 0; j < result_1.length(); j++) {
                                JSONObject collectData_1 = result_2.getJSONObject(j);
                                Sub_Question = collectData_1.getString("Sub_2_name");
                                list_questtion1_term1[j] = Sub_Question;
                            }
                        }
                        if(i==2){
                            list_questtion1_term1 = new String[result.length()+result_3.length()];
                            for (int j = 0; j < result_1.length(); j++) {
                                JSONObject collectData_1 = result_3.getJSONObject(j);
                                Sub_Question = collectData_1.getString("Sub_3_name");
                                list_questtion1_term1[j] = Sub_Question;
                            }
                        }
                        if(i==3){
                            list_questtion1_term1 = new String[result.length()+result_4.length()];
                            for (int j = 0; j < result_1.length(); j++) {
                                JSONObject collectData_1 = result_4.getJSONObject(j);
                                Sub_Question = collectData_1.getString("Sub_4_name");
                                list_questtion1_term1[j] = Sub_Question;
                            }
                        }
                    }

                    Log.d("onResponse", "onResponse: "+list_questtion1_term1.length);
                    Adapt_Makeassess adapter = new Adapt_Makeassess(list_questtion1_term1);
                    recyclerview.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//               Toast.makeText(main, "เข้าลูป", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), list_questtion1_term1.get(0), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
        return list;
    }


}
