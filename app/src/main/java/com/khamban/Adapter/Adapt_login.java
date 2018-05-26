package com.khamban.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khamban.assessment.R;
import com.squareup.picasso.Picasso;

public class Adapt_login extends RecyclerView.Adapter<Adapt_login.viewHolder>{
    String[] topics;
    String[] topics1;
    Context context;
    public Adapt_login(String[] topics,String[] topics1,Context context) {
        this.topics = topics;
        this.topics1 = topics1;
        this.context = context;
    }
    @Override
    public Adapt_login.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.from(context).inflate(R.layout.fragment_list_login, parent, false);
        viewHolder VHolder = new viewHolder(view);
        return VHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        ((viewHolder) holder).bindView(position);
//        title.set
    }

    @Override
    public int getItemCount() {
        return topics.length;
//        Toast.makeText(main, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView Img;
        Button btn1;
        //            private List<String> items;


        public viewHolder(View itemView) {
            super(itemView);
//            Img = (ImageView) itemView.findViewById(R.id.Img_login);
            title = (TextView) itemView.findViewById(R.id.txt_teacher);
//            btn1 = (Button)itemView.findViewById(R.id.order);

//            title.setOnClickListener(this);
        }

        public void bindView(int Position) {
            title.setText(topics[Position]);
//            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
//            Picasso.with(context).load("https://image.freepik.com/iconen-gratis/gebruiker-mannelijke-vorm-in-een-cirkel-ios-7-interface-symbool_318-35357.jpg").into(Img);
//            Picasso.with(context).load
//  ("https://image.freepik.com/iconen-gratis/gebruiker-mannelijke-vorm-in-een-cirkel-ios-7-interface-symbool_318-35357.jpg").fit().centerInside().into(Img);
//                items = mySQLConnect.getReport();
//            recyclerview.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);
//            title.setText(OurData.title[Position]);
//            title.setText(items[Position]);
        }
    }
}
