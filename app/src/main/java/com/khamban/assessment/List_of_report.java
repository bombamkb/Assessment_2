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
import com.khamban.Adapter.Adapt_Makeassess;
import com.khamban.Adapter.Adapt_Report;
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
public class List_of_report extends Fragment {
    private RecyclerView recyclerview;
    private List<String> items, list, list2, items2;
    private int index;

    public List_of_report(int index) {
        this.index = index;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_report, container, false);


        return view;
    }

    public List<String> getquesstion1(final int index) {
        String url = "http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php?status=0";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(String.format("Result_%d",index ));
                    String[] list_questtion1_term1 = new String[result.length()];
                    String Question = "", Sub_Question = "";
                    int k = 0;
                    ArrayList<Topic> topics = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collectData = result.getJSONObject(i);
                        Question = collectData.getString("Question");
                        JSONArray res = collectData.getJSONArray("Point_"+ index );
                        Log.d("onResponse2", "onResponse: " + Question);
                        list_questtion1_term1[i] = Question;
                        List<String> arr = new ArrayList<String>();
                        for (int j = 0; j < res.length(); j++) {
                            arr.add(res.get(j).toString());
                            Log.d("onResponse23", "onResponse: " + res.get(j));
                        }
                        topics.add(new Topic(Question,arr));
                    }
                    Log.d("onResponse24", "onResponse: " + list_questtion1_term1.length);
                    Adapt_Makeassess adapter = new Adapt_Makeassess(topics);//list_questtion1_term1
                    recyclerview.setAdapter(adapter);
//                    Adapt_Report adapter = new Adapt_Report(topics);//list_questtion1_term1
//                    recyclerview.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//               Toast.makeText(main, "เข้าลูป", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), list_questtion1_term1.get(0), Toast.LENGTH_SHORT).show();

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
