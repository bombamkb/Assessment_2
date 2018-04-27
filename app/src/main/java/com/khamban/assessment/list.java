package com.khamban.assessment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class list extends Fragment {
    String year[] = {"2560", "2559", "2558", "2557"};
    String term[] = {"1", "2"};


    public list() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        Spinner Year = (Spinner) view.findViewById(R.id.year);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,year);
        Year.setAdapter(adapter);

        Spinner Term = (Spinner) view.findViewById(R.id.term);
        ArrayAdapter adapter_2 = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,term);
        Term.setAdapter(adapter_2);
        List_detail fragment = new List_detail();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.list_detail, fragment);
        fragmentTransaction.commit();



        return view;

//        return inflater.inflate(R.layout.fragment_list, container, false);

    }

}
