package com.example.cs425.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs425.R;

import java.util.ArrayList;

public class UrgentAssignmentRecyclerViewAdapter extends RecyclerView.Adapter<UrgentAssignmentRecyclerViewAdapter.UrgentAssignmentViewHolder> {

    ArrayList<String> courseName = new ArrayList<>();
    ArrayList<String> assignmentName = new ArrayList<>();
    ArrayList<String> remainingTime = new ArrayList<>();

    Context context;

    public UrgentAssignmentRecyclerViewAdapter(Context context, ArrayList<String> courseName, ArrayList<String> assignmentName
            , ArrayList<String> remainingTime) {
        this.courseName = courseName;
        this.assignmentName = assignmentName;
        this.remainingTime = remainingTime;
        this.context = context;
    }
    @NonNull
    @Override
    public UrgentAssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.urgent_assignment_recycler_items,parent
                ,false);
        UrgentAssignmentRecyclerViewAdapter.UrgentAssignmentViewHolder viewHolder = new UrgentAssignmentRecyclerViewAdapter
                .UrgentAssignmentViewHolder(view);
        return  viewHolder ;
        }

    @Override
    public void onBindViewHolder(@NonNull UrgentAssignmentViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: " + courseName.size());
        holder.courseName.setText(courseName.get(position));
        holder.assignmentName.setText(assignmentName.get(position));
        holder.remainingTime.setText(remainingTime.get(position));
    }

    @Override
    public int getItemCount() {
        return assignmentName.size();
    }

    public class UrgentAssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        TextView assignmentName;
        TextView remainingTime;

        public UrgentAssignmentViewHolder(@NonNull View itemView /*, OnAssignmentListener onAssignmentListener*/) {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.courseName);
            assignmentName = (TextView) itemView.findViewById(R.id.assignment);
            remainingTime = (TextView) itemView.findViewById(R.id.remainingTime);

        }
    }
}
