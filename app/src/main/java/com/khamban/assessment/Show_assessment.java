package com.khamban.assessment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Show_assessment extends Fragment {
    private ListView dataViews;
    private MySQLConnect mySQLConnect;
    private List<String> items;
    public Show_assessment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_assessment,container,false);
        mySQLConnect = new MySQLConnect(getActivity());
        items = mySQLConnect.getAssessment();
        dataViews = (ListView)view.findViewById(R.id.Lis_2);
        dataViews.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,items)); //ทดแทนกันโดยเรียกใช้ adt แทน
    return view;
    }

}
