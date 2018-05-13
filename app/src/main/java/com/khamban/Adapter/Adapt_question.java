package com.khamban.Adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.khamban.assessment.MySQLConnect;
import com.khamban.assessment.R;
import com.khamban.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class Adapt_question extends RecyclerView.Adapter<Adapt_question.viewHolder_question> {
    private static String[] items;
    private List<String> topics,point;
    private int topic,opton;
    private Context main;

//    public Adapt_question(String[] items) {
//        this.items = items;
//    }

//    public Adapt_question(ArrayList<Topic> topics) {
//        this.topics = topics;
//    }

    public Adapt_question(List<String> topics, List<String> point,int option) {
        this.topics = topics;
        this.point = point;
        this.opton = option;
    }

    @Override
    public Adapt_question.viewHolder_question onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.fragment_list_question, parent, false);
        viewHolder_question VHolder = new viewHolder_question(view);
        main = parent.getContext();


        return VHolder;
    }

    @Override
    public void onBindViewHolder(Adapt_question.viewHolder_question holder, int position) {
        ((viewHolder_question) holder).bindView(position);
    }


    @Override
    public int getItemCount() {
        return topics.size();
    }


    public class viewHolder_question extends RecyclerView.ViewHolder {
        TextView title,point_of;
        Button btn1;
        String Question = "";
        RadioButton radi1,radi2,radi3,radi4,radi5;
        //            private List<String> items;
        private MySQLConnect mySQLConnect;


        public viewHolder_question(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Question_a);
            point_of = (TextView) itemView.findViewById(R.id.txt_point);
            radi1 = (RadioButton) itemView.findViewById(R.id.point_1);
            radi2 = (RadioButton) itemView.findViewById(R.id.point_2);
            radi3 = (RadioButton) itemView.findViewById(R.id.point_3);
            radi4 = (RadioButton) itemView.findViewById(R.id.point_4);
            radi5 = (RadioButton) itemView.findViewById(R.id.point_5);

            if (opton==1){
                point_of.setVisibility(View.VISIBLE);
                radi1.setVisibility(View.INVISIBLE);
                radi2.setVisibility(View.INVISIBLE);
                radi3.setVisibility(View.INVISIBLE);
                radi4.setVisibility(View.INVISIBLE);
                radi5.setVisibility(View.INVISIBLE);
            }
            if(opton==0){
                point_of.setVisibility(View.INVISIBLE);
                radi1.setVisibility(View.VISIBLE);
                radi2.setVisibility(View.VISIBLE);
                radi3.setVisibility(View.VISIBLE);
                radi4.setVisibility(View.VISIBLE);
                radi5.setVisibility(View.VISIBLE);
            }

        }

        public void bindView(int Position) {
            for (int i = 0; i <topics.get(Position).length(); i++){
                title.setText(topics.get(Position));
                point_of.setText(point.get(Position));
                Log.d("Topic1A", "index: "+Position+"Topic1A: " + topics.get(Position)+point.get(Position));
                Log.d("Topic1", "index: "+Position+"Topic1: " + topics.get(Position).length());
            }

        }

    }
}
