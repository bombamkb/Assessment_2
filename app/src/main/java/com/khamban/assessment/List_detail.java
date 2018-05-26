package com.khamban.assessment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class List_detail extends Fragment {


    private RecyclerView recyclerview;
    private ListView dataViews;
    private Button Btn;
    private MySQLConnect mySQLConnect;
    private List<String> items,list,list2,list_questtion1_term1,list_questtion1_term2;
    private String URL ="http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php?status=0";

    public List_detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_detail, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.Recy_2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        items = getAssessment();


        return view;
    }
    public List<String> getAssessment(){
        String url = URL;
//        Toast.makeText(getActivity(), "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                showAssessment(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result =  jsonObject.getJSONArray("Assessment");
                    String evaName = "";
                    String[] data = new String[result.length()];
                    for (int i = 0 ; i < result.length();i++){
                        JSONObject collectData = result.getJSONObject(i);
                        evaName = collectData.getString("name");
                        Log.d("onResponse", "onResponse: "+evaName);
//                        list.add("xxx");
                        data[i] = evaName;
                    }
                    Log.d("onResponse", "onResponse: "+data.length);
                    Adapt_Assessment adapter = new Adapt_Assessment(data);
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
        return list;
    }
    public void showAssessment(String response){
//        Toast.makeText(getActivity(), "เข้าลูป 2", Toast.LENGTH_SHORT).show();
        String evaName ="";

        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result =  jsonObject.getJSONArray("Assessment_of");

            for (int i = 0 ; i < result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                evaName = collectData.getString("name");
                list.add(evaName);
            }
           /* Adapt_Assessment adapter = new Adapt_Assessment(list);
            recyclerview.setAdapter(adapter);*/
        }catch (JSONException ex){ex.printStackTrace();}

    }



}
