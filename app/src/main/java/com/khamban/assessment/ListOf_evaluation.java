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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khamban.Adapter.Adapt_Assessment;
import com.khamban.Adapter.Adapt_Makeassess;
import com.khamban.Adapter.Adapt_question;
import com.khamban.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ListOf_evaluation extends Fragment {


//    private final Object sub_questtion1_term1;
    private String URL ="http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php?status=0";
    private List<String> items,list,list2,items2;
    private String[] toppings,data;
    private ListView dataViews;
    private RecyclerView recyclerview_question;
    private int index;

    public ListOf_evaluation(int index) {
        this.index = index;
        // Required empty public constructor
//        this.sub_questtion1_term1 = sub_questtion1_term1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_evaluation, container, false);

        recyclerview_question = (RecyclerView) view.findViewById(R.id.Recy_radio);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview_question.setLayoutManager(layoutManager);
        items = getquesstion1(index);

//        dataViews = (ListView) view.findViewById(R.id.Lis_sub);
//        items2 = getSubquestion();
//        dataViews.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,items2)); //ทดแทนกันโดยเรียกใช้ adt แทน


        return view;
    }

    public List<String> getquesstion1(final int index) {
        String url = URL;
//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

//        final int finalIndex = index;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(String.format("Topic_term%d", index));
//

                    String[] list_questtion1_term1 = new String[result.length()];
                    String Question = "";
                    int k = 0;
                    ArrayList<Topic> topics = new ArrayList<>();
                    ArrayList<Topic> topics1 = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collectData = result.getJSONObject(i);
                        Question = collectData.getString("Question");
                        JSONArray res = collectData.getJSONArray("Sub_"+ index +"_name");
//

                        Log.d("onResponse", "onResponse: " + Question);
                        list_questtion1_term1[i] = Question;

                        List<String> arr = new ArrayList<String>();
                        List<String> arr1 = new ArrayList<String>();
                        for (int j = 0; j < res.length(); j++) {
                            arr.add(res.get(j).toString());
                            Log.d("onResponse", "onResponse: " + res.get(j));
                        }
//                        topics1.add(new Topic(Point_ques,arr1));
                        topics.add(new Topic(Question,arr));
                        k= arr.size();
                    }
                    Log.d("onResponse", "onResponse: " + list_questtion1_term1.length);
                    Adapt_Makeassess adapter = new Adapt_Makeassess(topics);//list_questtion1_term1
                    recyclerview_question.setAdapter(adapter);

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
