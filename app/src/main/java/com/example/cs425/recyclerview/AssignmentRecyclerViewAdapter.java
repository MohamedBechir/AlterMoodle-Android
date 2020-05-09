package com.example.cs425.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs425.R;

import java.util.ArrayList;

public class AssignmentRecyclerViewAdapter extends RecyclerView.Adapter<AssignmentRecyclerViewAdapter.AssignmentViewHolder>{


    private static final String TAG = "AssignmentRecyclerViewA";
    ArrayList<String> assignmentsName = new ArrayList<>();
    Context context;

    public AssignmentRecyclerViewAdapter( Context context,ArrayList<String> assignmentsName){
        this.assignmentsName = assignmentsName;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_recycler_items,parent,false);
        AssignmentViewHolder viewHolder = new AssignmentViewHolder(view);
        return  viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {

        holder.assignmentName.setText(assignmentsName.get(position));
    }

    @Override
    public int getItemCount() {
        return assignmentsName.size();
    }


    public class AssignmentViewHolder extends RecyclerView.ViewHolder {

        TextView assignmentName;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentName = (TextView) itemView.findViewById(R.id.assignmentName);

        }
    }

}
