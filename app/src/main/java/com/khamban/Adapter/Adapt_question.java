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
import android.widget.Toast;

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

    public Adapt_question(List<String> topics) {
        this.topics = topics;
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
        RadioGroup group;
        //            private List<String> items;
        private MySQLConnect mySQLConnect;


        public viewHolder_question(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Question_a);
            radi1 = (RadioButton) itemView.findViewById(R.id.point_1);
            radi2 = (RadioButton) itemView.findViewById(R.id.point_2);
            radi3 = (RadioButton) itemView.findViewById(R.id.point_3);
            radi4 = (RadioButton) itemView.findViewById(R.id.point_4);
            radi5 = (RadioButton) itemView.findViewById(R.id.point_5);
            group = (RadioGroup) itemView.findViewById(R.id.Radio_point);


            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case (R.id.point_1):
                            final int index = (getAdapterPosition())+1;
                            Toast.makeText(itemView.getContext(), "ข้อที่: "+ index +"ได้ 5 คะแนน", Toast.LENGTH_SHORT).show();

                            break;
                        case (R.id.point_2):
                            final int index2 = (getAdapterPosition())+1;
                            Toast.makeText(itemView.getContext(), "ข้อที่: "+ index2 +"ได้ 4 คะแนน", Toast.LENGTH_SHORT).show();
//                            visualScore[position] = 0;
//                            audioScore[position] = 1;
//                            kinestScore[position] = 0;
                            break;
                        case (R.id.point_3):
                            final int index3 = (getAdapterPosition())+1;
                            Toast.makeText(itemView.getContext(), "ข้อที่: "+ index3 +"ได้ 3 คะแนน", Toast.LENGTH_SHORT).show();
//                            visualScore[position] = 0;
//                            audioScore[position] = 0;
//                            kinestScore[position] = 1;
                            break;
                        case (R.id.point_4):
                            final int index4 = (getAdapterPosition())+1;
                            Toast.makeText(itemView.getContext(), "ข้อที่: "+ index4 +"ได้ 2 คะแนน", Toast.LENGTH_SHORT).show();
//                            visualScore[position] = 0;
//                            audioScore[position] = 0;
//                            kinestScore[position] = 1;
                            break;
                        case (R.id.point_5):
                            final int index5 = (getAdapterPosition())+1;
                            Toast.makeText(itemView.getContext(), "ข้อที่: "+ index5 +"ได้ 1 คะแนน", Toast.LENGTH_SHORT).show();
//                            visualScore[position] = 0;
//                            audioScore[position] = 0;
//                            kinestScore[position] = 1;
                            break;
                    }
                }
            });

        }

        public void bindView(int Position) {
            for (int i = 0; i <topics.get(Position).length(); i++){
                title.setText(topics.get(Position));
//                point_of.setText(point.get(Position));
//                Log.d("Topic1A", "index: "+Position+"Topic1A: " + topics.get(Position)+point.get(Position));



                Log.d("Topic1", "index: "+Position+"Topic1: " + topics.get(Position).length());
            }

        }

        public void onClick(final View view) {
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.point_1:
                            Toast.makeText(view.getContext(), "Click: Point1", Toast.LENGTH_SHORT).show();
                            // do operations specific to this selection
                            break;
                        case R.id.point_2:
                            // do operations specific to this selection
                            break;
                        case R.id.point_3:
                            // do operations specific to this selection
                            break;
                        case R.id.point_4:
                            // do operations specific to this selection
                            break;
                        case R.id.point_5:
                            // do operations specific to this selection
                            break;
                    }
                }
            });
        }
    }
}
