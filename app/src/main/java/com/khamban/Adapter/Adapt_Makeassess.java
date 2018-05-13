package com.khamban.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.khamban.assessment.ListOf_evaluation;
import com.khamban.assessment.R;
import com.khamban.model.Topic;

import java.util.ArrayList;

public class Adapt_Makeassess extends RecyclerView.Adapter<Adapt_Makeassess.viewHolderMake> {
    private static String[] Question, sub;
    private ArrayList<Topic> topics;
    private Context main,main2;

//    public Adapt_Makeassess(String[] Question) {
//        this.Question = Question;
////        this.sub = sub;
//    }

    public Adapt_Makeassess(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public Adapt_Makeassess.viewHolderMake onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.fragment_list_of_evaluation, parent, false);
        viewHolderMake VHolder = new viewHolderMake(view);
        main = parent.getContext();
        return VHolder;
    }

    @Override
    public void onBindViewHolder(viewHolderMake holder, int position) {
        ((viewHolderMake) holder).bindView(position);
    }


    @Override
    public int getItemCount() {
        return topics.size();
    }


    public class viewHolderMake extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, title3;
        ListView title2;
        RecyclerView title4;

        public viewHolderMake(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Head_question);
            title4 = (RecyclerView) itemView.findViewById(R.id.Recy_radio);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(main);
            title4.setLayoutManager(layoutManager);

            title.setOnClickListener(this);
        }

        public void bindView(int Position) {

            title.setText(topics.get(Position).getQuestion());//Question[Position]
                Adapt_question adapter = new Adapt_question(topics.get(Position).getSubName(), topics.get(Position).getPoint(),1);
                title4.setAdapter(adapter);
            Log.d("onResponse1", "bindView1: " + topics.get(Position).getSubName());
        }


        @Override
        public void onClick(final View view) {

            PopupMenu popupMenu = new PopupMenu(view.getContext(), title);
            popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_detail:
                            topics.indexOf(title);
                            Toast.makeText(view.getContext(), "Item: "+topics.indexOf(topics), Toast.LENGTH_SHORT).show();
//                            ListOf_evaluation List = new ListOf_evaluation();
//                            FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
//                            manager.beginTransaction().replace(R.id.main, List).commit();
                            return true;
                        case R.id.menu_detail_1:
                            Toast.makeText(view.getContext(), "Item", Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            return false;
                    }
                }
            });

            popupMenu.show();
        }
    }
}
