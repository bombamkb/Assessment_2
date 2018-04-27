package com.khamban.assessment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Item_list extends Fragment implements View.OnClickListener{
    private Button Btn;
//    TextView order = (TextView) getView().findViewById(R.id.order_list);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
//        Btn = (Button) view.findViewById(R.id.order);
//        Btn.setOnClickListener(this);


//        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
//        popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
//
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.menu_detail:
//                        Toast.makeText(getActivity(),"Item 1" ,Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_detail_1:
//                        Toast.makeText(getActivity(),"Item 1" ,Toast.LENGTH_SHORT).show();
//                        return true;
//                    default:
//                        return false;
//                }
//
//            }
//        });
//        popupMenu.show();
//        btn.setOnClickListener(listener);


        return view;

    }
//    public void Showpop(View view) {
//        PopupMenu popupMenu = new PopupMenu(getActivity().getBaseContext(),view);
//        popupMenu.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) getActivity());
//        popupMenu.inflate(R.menu.popup);
//        popupMenu.show();
//
//    }



    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(getActivity(),Btn);
        popupMenu.getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(),""+item.getTitle() ,Toast.LENGTH_SHORT).show();
                return  true;
            }
        });

        popupMenu.show();
    }
}