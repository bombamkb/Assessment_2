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
import com.khamban.Adapter.Adapt_MakeReport;
import com.khamban.Adapter.Adapt_Makeassess;
import com.khamban.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class List_card_of_report extends Fragment {


    private RecyclerView recyclerview_report;
    private List<String> items,list;
    private int index;

    public List_card_of_report(int index) {
        this.index = index;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_card_of_report, container, false);

        recyclerview_report = (RecyclerView) view.findViewById(R.id.Recy_radio);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview_report.setLayoutManager(layoutManager);
        items = getquesstion1(index);
        return view;
    }
    public List<String> getquesstion1(final int index) {
        String url = "http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php?status=0";
//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

//        final int finalIndex = index;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(String.format("Topic_term%d", index));

                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONArray result1 = jsonObject1.getJSONArray(String.format("Result_%d", index));
//

                    String[] list_questtion1_term1 = new String[result.length()];
                    String[] Point = new String[result1.length()];
                    String Question = "",Point_que = "";
                    int k = 0;
                    ArrayList<Topic> topics = new ArrayList<>();
                    ArrayList<Topic> topics1 = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collectData = result.getJSONObject(i);
                        Question = collectData.getString("Question");
                        JSONArray res = collectData.getJSONArray("Sub_"+ index +"_name");

                        JSONObject collectData1 = result1.getJSONObject(i);
                        Question = collectData1.getString("Question");
                        JSONArray res1 = collectData1.getJSONArray("Point_"+ index);

                        Log.d("onResponse", "onResponse: " + Question);
                        list_questtion1_term1[i] = Question;
//                        list_questtion1_term1[i] = Question;

                        List<String> arr = new ArrayList<String>();
                        List<String> arr1 = new ArrayList<String>();
                        for (int j = 0; j < res.length(); j++) {
                            arr.add(res.get(j).toString());
                            arr1.add(res1.get(j).toString());
                            Log.d("onResponse :>>", "onResponse:>> " + res.get(j));
                        }
//                        topics1.add(new Topic(Point_ques,arr1));
                        topics.add(new Topic(Question,arr));
                        topics1.add(new Topic(Question,arr1));
                        k= arr.size();
                    }
                    Log.d("onResponse3", "onResponse3: " + list_questtion1_term1.length);
                    Adapt_MakeReport adapter = new Adapt_MakeReport(topics,topics1);//list_questtion1_term1
                    recyclerview_report.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
        return list;
    }

}
