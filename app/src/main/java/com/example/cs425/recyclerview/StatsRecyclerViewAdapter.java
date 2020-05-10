package com.example.cs425.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.cs425.R;


import java.util.ArrayList;

public class StatsRecyclerViewAdapter extends RecyclerView.Adapter<StatsRecyclerViewAdapter.StatsViewHolder> {

     ArrayList<String> coursesCodes = new ArrayList<>();
     ArrayList<String> coursesOverall = new ArrayList<>();
    private ArrayList<String> coursesNames= new ArrayList<>();
    private Context context;

    public StatsRecyclerViewAdapter(Context context, ArrayList<String> coursesCodes
            , ArrayList<String> coursesOverall, ArrayList<String> coursesNames) {
        this.context = context;
        this.coursesCodes = coursesCodes;
        this.coursesOverall = coursesOverall;
        this.coursesNames = coursesNames;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_recycler_items,parent,false);
        StatsRecyclerViewAdapter.StatsViewHolder viewHolder = new StatsRecyclerViewAdapter
                .StatsViewHolder(view);
        return  viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {

        holder.overall.setVisibility(View.GONE);
        ArrayList<String> clicked = new ArrayList<>();

        holder.code.setText(coursesCodes.get(position));
        holder.name.setText(coursesNames.get(position));
        holder.overall.setText(coursesOverall.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.add(coursesCodes.get(position));
                holder.overall.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coursesCodes.size();
    }


    public class StatsViewHolder extends ViewHolder {

        TextView code;
        TextView name;
        TextView overall;

        public StatsViewHolder(@NonNull View itemView) {

            super(itemView);
            code = (TextView) itemView.findViewById(R.id.course_code_stats);
            name = (TextView) itemView.findViewById(R.id.course_name_stats);
            overall = (TextView) itemView.findViewById(R.id.overall);

        }

    }
}
