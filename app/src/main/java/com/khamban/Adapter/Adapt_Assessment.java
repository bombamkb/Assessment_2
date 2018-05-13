package com.khamban.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.khamban.assessment.Evaluation;
import com.khamban.assessment.ListOf_evaluation;
import com.khamban.assessment.MySQLConnect;
import com.khamban.assessment.R;
import com.khamban.assessment.Report;

public class Adapt_Assessment extends RecyclerView.Adapter<Adapt_Assessment.viewHolder> {
    private static String[] items;

    public Adapt_Assessment(String[] items) {
        this.items = items;
    }

        @Override
        public Adapt_Assessment.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.from(parent.getContext()).inflate(R.layout.fragment_item_list, parent, false);
            viewHolder VHolder = new viewHolder(view);
            return VHolder;
        }

    @Override
        public void onBindViewHolder(viewHolder holder, int position) {
            ((viewHolder) holder).bindView(position);
        }


        @Override
        public int getItemCount() {
            return items.length;
        }


        public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            Button btn1;
//            private List<String> items;
            private MySQLConnect mySQLConnect;


            public viewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.order_list);
//            btn1 = (Button)itemView.findViewById(R.id.order);

                title.setOnClickListener(this);
            }

            public void bindView(int Position) {
//                items = mySQLConnect.getReport();
//            recyclerview.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);
//            title.setText(OurData.title[Position]);
                title.setText(items[Position]);
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
                                int index = 2;
                                if(items[getAdapterPosition()].toString().equals("แบบประเมินการจัดการเรียนการสอนภาคทฤษฎี และสิ่งสนับสนุนการเรียนรู้"))
                                {
                                    index = 1;
                                }
                                Toast.makeText(view.getContext(), "Item" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                                Report List = new Report(index);
                                FragmentManager manager = ((FragmentActivity)view.getContext()).getSupportFragmentManager();
                                manager.beginTransaction().replace(R.id.main, List).commit();
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
