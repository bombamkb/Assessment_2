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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khamban.Adapter.Adapt_Assessment;
import com.khamban.Adapter.Adapt_Makeassess;
import com.khamban.Adapter.Adapt_Makeassess;
import com.khamban.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Evaluation extends Fragment implements View.OnClickListener{
    private List<String> items, list, list2, items2;
    private String URL = "http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php";
    private RecyclerView recyclerview;
    private ListView dataViews;
    int index,option;
    Button submit;


    public Evaluation(int index) {
        this.index = index;
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
        items = getquesstion1(index);
        submit = (Button)  view.findViewById(R.id.sub);

        View.OnClickListener onClickListener;
        submit.setOnClickListener(this);



        return view;
    }

    public List<String> getquesstion1(final int index) {
        String url = URL;

//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

//        final int finalIndex;
        Toast.makeText(getActivity(), ""+index, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(String.format("Topic_term%d", index));
                    Log.d("Topic_term", "Topic_term: " + index);
                    String[] list_questtion1_term1 = new String[result.length()];
                    String Question = "";
                    String[] list_point = new String[result.length()];
                    String Point_ques = "";
                    int k = 0;
                    ArrayList<Topic> topics = new ArrayList<>();
                    ArrayList<Topic> topics1 = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collectData = result.getJSONObject(i);
                        Question = collectData.getString("Question");
                        JSONArray res = collectData.getJSONArray("Sub_"+ index +"_name");

                        Log.d("onResponse", "onResponse: " + Question);
                        list_questtion1_term1[i] = Question;
                        list_point[i] = Point_ques;
                        List<String> arr = new ArrayList<String>();
                        List<String> arr1 = new ArrayList<String>();
                        for (int j = 0; j < res.length(); j++) {
                            arr.add(res.get(j).toString());
                            Log.d("onResponse", "onResponse: " + res.get(j));
                        }
//                        topics1.add(new Topic(Point_ques,arr1));
                        topics.add(new Topic(Question,arr));
                        k = arr.size();
                    }
                    Log.d("onResponse", "onResponse: " + list_questtion1_term1.length);
                    Adapt_Makeassess adapter = new Adapt_Makeassess(topics);//list_questtion1_term1
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
//                Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
        return list;
    }

    @Override
    public void onClick(View view) {

    }
}
