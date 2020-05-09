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
import com.example.cs425.fragments.StatsFragment;

import java.util.ArrayList;

public class StatsRecyclerViewAdapter extends RecyclerView.Adapter<StatsRecyclerViewAdapter.StatsViewHolder> {

     ArrayList<String> coursesCodes = new ArrayList<>();
     ArrayList<String> coursesNames = new ArrayList<>();
     ArrayList<String> coursesOverall = new ArrayList<>();
    private Context context;

    public StatsRecyclerViewAdapter(Context context, ArrayList<String> coursesCodes, ArrayList<String> coursesNames,
                                    ArrayList<String> coursesOverall) {
        this.context = context;
        this.coursesCodes = coursesCodes;
        this.coursesNames = coursesNames;
        this.coursesOverall = coursesOverall;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_recycler_items,parent,false);
        StatsRecyclerViewAdapter.StatsViewHolder viewHolder = new StatsRecyclerViewAdapter
                .StatsViewHolder(view/*, onAssignmentListener*/);
        return  viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {

        holder.overall.setVisibility(View.GONE);

        holder.code.setText(coursesCodes.get(position));
        holder.name.setText(coursesNames.get(position));
        holder.overall.setText(coursesOverall.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               holder.overall.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coursesNames.size();
    }


    public class StatsViewHolder extends ViewHolder {

        TextView name;
        TextView code;
        TextView overall;

        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
            code = (TextView) itemView.findViewById(R.id.course_code_stats);
            name = (TextView) itemView.findViewById(R.id.course_name_stats);
            overall = (TextView) itemView.findViewById(R.id.overall);
        }
    }


}
