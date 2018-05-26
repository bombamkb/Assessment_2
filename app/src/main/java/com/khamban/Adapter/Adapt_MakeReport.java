package com.khamban.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.khamban.assessment.R;
import com.khamban.model.Topic;

import java.util.ArrayList;

public class Adapt_MakeReport extends RecyclerView.Adapter<Adapt_MakeReport.viewHolderMake>  {
    private static String[] Question, sub;
    private ArrayList<Topic> topics,point;
    private Context main,main2;
    private int option,count;

//    public Adapt_Makeassess(String[] Question) {
//        this.Question = Question;
////        this.sub = sub;
//    }

    public Adapt_MakeReport(ArrayList<Topic> topics,ArrayList<Topic> point) {

        this.topics = topics;
        this.point = point;
    }

    @Override
    public Adapt_MakeReport.viewHolderMake onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.fragment_list_card_of_report, parent, false);
        Adapt_MakeReport.viewHolderMake VHolder = new Adapt_MakeReport.viewHolderMake(view);
        main = parent.getContext();
        return VHolder;
    }

    @Override
    public void onBindViewHolder(Adapt_MakeReport.viewHolderMake holder, int position) {
        ((Adapt_MakeReport.viewHolderMake) holder).bindView(position);
    }


    @Override
    public int getItemCount() {
        return topics.size();
    }


    public class viewHolderMake extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, title3;
        ListView title2;
        RecyclerView title4;

        public viewHolderMake(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Head_question);
            title4 = (RecyclerView) itemView.findViewById(R.id.Recy_report_1);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(main);
            title4.setLayoutManager(layoutManager);

            title.setOnClickListener(this);
        }

        public void bindView(int Position) {

            title.setText(topics.get(Position).getQuestion());//Question[Position]
//            if(option==0) {
//                Adapt_question adapter = new Adapt_question(topics.get(Position).getSubName(), option);
//                title4.setAdapter(adapter);
//            }

                Adapt_Report adapter = new Adapt_Report(topics.get(Position).getSubName(),point.get(Position).getSubName());
                title4.setAdapter(adapter);

            Log.d("Point", "bindView1: " + topics.get(Position).getPoint());
            Log.d("topics", "topics: " + topics.get(Position).getSubName());
            Log.d("Point2", "bindView2: " + point.get(Position).getPoint());
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
