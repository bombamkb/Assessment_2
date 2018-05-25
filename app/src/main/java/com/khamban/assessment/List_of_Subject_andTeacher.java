package com.khamban.assessment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class List_of_Subject_andTeacher extends Fragment {
    TextView status_tech;
    String User;


    public List_of_Subject_andTeacher() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of__subject_and_teacher, container, false);
//        status_tech = (TextView) view.findViewById(R.id.status_tech);
//        View.OnClickListener onClickListener;
//        status_tech.setOnClickListener(this);
//        User = getFragmentManager().getStringExtra("Username");

        return view;
    }


}

