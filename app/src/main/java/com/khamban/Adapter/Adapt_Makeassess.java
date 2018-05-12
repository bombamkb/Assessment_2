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
//        int size = Question.length*sub.length;
        return topics.size();
    }


    public class viewHolderMake extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, title3;
        ListView title2;
        RecyclerView title4;
//        Button btn1;
        //            private List<String> items;
//    private MySQLConnect mySQLConnect;


        public viewHolderMake(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Head_question);
//            title2 = (ListView) itemView.findViewById(R.id.Lis_eva);
            title4 = (RecyclerView) itemView.findViewById(R.id.Recy_radio);
//            title3 = (TextView) itemView.findViewById(R.id.Question);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(main);
            title4.setLayoutManager(layoutManager);

//            title_sub = (TextView) itemView.findViewById(R.id.Sub_question);

//            title_sub = (TextView) itemView.findViewById(R.id.Sub_question);
//            btn1 = (Button)itemView.findViewById(R.id.order);

            title.setOnClickListener(this);
        }

        public void bindView(int Position) {
//                items = mySQLConnect.getReport();
//            recyclerview.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);
//            title.setText(OurData.title[Position]);
//            title.setText(items[0]);

            title.setText(topics.get(Position).getQuestion());//Question[Position]
//            if(Position==0) {
                Adapt_question adapter = new Adapt_question(topics.get(Position).getSubName(), topics.get(Position).getPoint(),0);
                title4.setAdapter(adapter);
//            }

//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(main);
//            title2.setLayoutManager(layoutManager);
//            title2.setLayoutManager(layoutManager);
//            title3.setText((CharSequence) topics.get(Position).getSubName());
//            for (int i = 0; i < topics.get(Position).getSubName().size(); i++) {
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(main, android.R.layout.simple_list_item_1, topics.get(Position).getSubName());
//            title2.setAdapter(adapter);
//            title2.setAdapter(new ArrayAdapter(main, android.R.layout.simple_list_item_2, topics.get(Position).getSubName()));
//                title3.setText(topics.get(i).getSubName().toString());
//            }
//            for (int i = 0; i < topics.get(Position).getSubName().size(); i++)
            Log.d("onResponse1", "bindView1: " + topics.get(Position).getSubName());
//                title2.getAdapter();

//                title2.setAdapter(new ArrayAdapter<String>(main, android.R.layout.simple_list_item_1, topics.get(Position).getSubName()));

                //                        setText(Question[Position]);

//                title_sub.setText(sub[Position]);


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
