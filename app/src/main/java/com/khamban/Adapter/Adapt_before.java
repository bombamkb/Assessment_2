package com.khamban.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.khamban.assessment.Evaluation;
import com.khamban.assessment.R;
import com.khamban.assessment.Report;

public class Adapt_before extends RecyclerView.Adapter<Adapt_before.viewHolder> {
    String[] topics;
    String[] topics1;
    String[] topics2;
    String[] topics3;
    Context context;
    public Adapt_before(String[] topics,String[] topics1,String[] topics2,String[] topics3) {
        this.topics = topics;
        this.topics1 = topics1;
        this.topics2 = topics2;
        this.topics3 = topics3;
    }
    @Override
    public Adapt_before.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.fragment_list_of__subject_and_teacher, parent, false);
        Adapt_before.viewHolder VHolder = new Adapt_before.viewHolder(view);
        return VHolder;
    }

    @Override
    public void onBindViewHolder(Adapt_before.viewHolder holder, int position) {
        ((Adapt_before.viewHolder) holder).bindView(position);
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
                    Report List = new Report(Integer.parseInt(topics3[0]));
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

                status.setTextColor(Color.BLUE);

//            Picasso.with(context).load(topics1[Position]).fit().centerInside().into(Img);
//                items = mySQLConnect.getReport();
//            recyclerview.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);
//            title.setText(OurData.title[Position]);
//            title.setText(items[Position]);
        }



    }
}
