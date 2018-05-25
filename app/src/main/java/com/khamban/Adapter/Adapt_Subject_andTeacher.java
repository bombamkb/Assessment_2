package com.khamban.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khamban.assessment.Evaluation;
import com.khamban.assessment.ListOf_evaluation;
import com.khamban.assessment.Main2Activity;
import com.khamban.assessment.R;
import com.khamban.assessment.Report;
import com.khamban.assessment.Subject_andTeac;
import com.squareup.picasso.Picasso;

public class Adapt_Subject_andTeacher extends RecyclerView.Adapter<Adapt_Subject_andTeacher.viewHolder> {
    String[] topics;
    String[] topics1;
    String[] topics2;
    String[] topics3;
    Context context;
    public Adapt_Subject_andTeacher(String[] topics,String[] topics1,String[] topics2,String[] topics3) {
        this.topics = topics;
        this.topics1 = topics1;
        this.topics2 = topics2;
        this.topics3 = topics3;
    }
    @Override
    public Adapt_Subject_andTeacher.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.fragment_list_before, parent, false);
        Adapt_Subject_andTeacher.viewHolder VHolder = new Adapt_Subject_andTeacher.viewHolder(view);
        return VHolder;
    }

    @Override
    public void onBindViewHolder(Adapt_Subject_andTeacher.viewHolder holder, int position) {
        ((Adapt_Subject_andTeacher.viewHolder) holder).bindView(position);
//        title.set
    }

    @Override
    public int getItemCount() {
        return topics.length;

    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name_tech,subject,status;
        ImageView Img;
        Button btn1;
        //            private List<String> items;


        public viewHolder(View itemView) {
            super(itemView);
            name_tech = (TextView) itemView.findViewById(R.id.name_tech);
            subject = (TextView) itemView.findViewById(R.id.Head_question);
            status = (TextView) itemView.findViewById(R.id.status_tech);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                            Evaluation List = new Evaluation(Integer.parseInt(topics3[0]));
                            FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.main, List).commit();
                }
            });
//            btn1 = (Button)itemView.findViewById(R.id.order);

//            title.setOnClickListener(this);
        }

        public void bindView(int Position) {
            subject.setText(topics[Position]);
            name_tech.setText(topics1[Position]);
            status.setText(topics2[Position]);

            status.setTextColor(Color.RED);
//            Picasso.with(context).load(topics1[Position]).fit().centerInside().into(Img);
//                items = mySQLConnect.getReport();
//            recyclerview.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);
//            title.setText(OurData.title[Position]);
//            title.setText(items[Position]);
        }



    }
}
