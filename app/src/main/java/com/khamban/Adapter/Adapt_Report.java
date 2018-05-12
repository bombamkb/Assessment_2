package com.khamban.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.khamban.assessment.MySQLConnect;
import com.khamban.assessment.R;

import java.util.List;

public class Adapt_Report extends RecyclerView.Adapter<Adapt_Report.viewHolder_report> {
    private static String[] items;
    private List<String> topics;
    private int topic;
    private Context main;

//    public Adapt_question(String[] items) {
//        this.items = items;
//    }

//    public Adapt_question(ArrayList<Topic> topics) {
//        this.topics = topics;
//    }

    public Adapt_Report(List<String> topics) {
        this.topics = topics;
        this.topic = topic;
    }

    @Override
    public Adapt_Report.viewHolder_report onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.fragment_list_of_report, parent, false);
        Adapt_Report.viewHolder_report VHolder = new Adapt_Report.viewHolder_report(view);
        main = parent.getContext();


        return VHolder;
    }

    @Override
    public void onBindViewHolder(Adapt_Report.viewHolder_report holder, int position) {
        ((Adapt_Report.viewHolder_report) holder).bindView(position);
//            for (int i = 0; i < topics.get(position).getSubName().size(); i++) {
//                holder.title.setText(topics.get(i).getSubName().get(i));
//                Log.d("Topic1A", "index: " + position + "Topic1A: " + topics.get(position).getSubName().get(i));
//                Log.d("Topic1", "index: " + position + "Topic1: " + topics.get(position).getSubName().size());
//
//
//            }
//        position++;



//        holder.title.setText(topics.get(i).getSubName().get(i));
//        holder.title.setText(topics.get(position).getSubName().get(i));
    }


    @Override
    public int getItemCount() {
        return topics.size();
    }


    public class viewHolder_report extends RecyclerView.ViewHolder {
        TextView title,title2;
        Button btn1;
        String Question = "";
        //            private List<String> items;
        private MySQLConnect mySQLConnect;


        public viewHolder_report(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_ques);
            title2 = (TextView) itemView.findViewById(R.id.list_point);
//            btn1 = (Button)itemView.findViewById(R.id.order);

        }

        public void bindView(int Position) {
            for (int i = 0; i <topics.get(Position).length(); i++){
                title.setText(topics.get(Position));
                Log.d("Topic1A", "index: "+Position+"Topic1A: " + topics.get(Position));
                Log.d("Topic1", "index: "+Position+"Topic1: " + topics.get(Position).length());
//
//
            }
//            Position++;
//            String[] value = new String[topics.get(Position).getSubName().size()];
//            for(String str : topics.get(Position).getSubName()){
//                value += str + "\n";
//            }
//            title.setText(value);

//            for (int i = 0; i <topics.get(Position).getSubName().size(); i++){
//                title.setText(topics.get(Position).getSubName().toString());
//                Log.d("Topic", "Topic: " + Position);
//            }
//            Log.d("Topic2", "Topic2: " + Position);
//            title.setText(value[Position]);
//            title.setText(topics.get(Position).getSubName().get(0));

//            title.setText(items[Position]);
        }

    }
}


