package com.khamban.assessment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Show_assessment extends Fragment {
    private ListView dataViews;
    private MySQLConnect mySQLConnect;
    private List<String> items;
    private String URL ="http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php";

    public Show_assessment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_assessment,container,false);
        mySQLConnect = new MySQLConnect(getActivity());
//        items = mySQLConnect.getAssessment();
        getAssessment();
        dataViews = (ListView)view.findViewById(R.id.Lis_2);

    return view;
    }

    public void getAssessment(){
        String url = URL;
        Toast.makeText(getActivity(), "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("Assessment");
                    String[] arr = new String[result.length()];
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collectData = result.getJSONObject(i);
                        String evaName = collectData.getString("name");
//                        items.add(evaName);
                        arr[i] = evaName;
                    }
                    dataViews.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arr));//ทดแทนกันโดยเรียกใช้ adt แทน
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
//                showAssessment(response);
                Toast.makeText(getActivity(), "เข้าลูป", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), items.get(0), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        );
//
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
