package com.khamban.assessment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ListOf_evaluation extends Fragment {


//    private final Object sub_questtion1_term1;
    private String URL ="http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php?status=0";
    private List<String> items,list2,items2;
    private ListView dataViews;

    public ListOf_evaluation() {
        // Required empty public constructor
//        this.sub_questtion1_term1 = sub_questtion1_term1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_evaluation, container, false);

        dataViews = (ListView) view.findViewById(R.id.Lis_2);
        items2 = getSubquestion();
        dataViews.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,items2));


        return view;
    }
    public List<String> getSubquestion(){
        String url = URL;
        Toast.makeText(getActivity(), "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                showAssessment(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result =  jsonObject.getJSONArray("Topic_term2");
                    String evaName = "";
                    String[] data = new String[result.length()];
                    for (int i = 0 ; i < result.length();i++){
                        JSONObject collectData = result.getJSONObject(i);
                        evaName = collectData.getString("Sub_1_name");
                        Log.d("onResponse", "onResponse: "+evaName);
//                        list.add("xxx");
                        data[i] = evaName;
                    }
                    Log.d("onResponse", "onResponse: "+data.length);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //ทดแทนกันโดยเรียกใช้ adt
                Toast.makeText(getActivity(), "เข้าลูป", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), list.get(0), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        );
//
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
        return list2;
    }



}
