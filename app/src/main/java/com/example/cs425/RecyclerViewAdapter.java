package com.example.cs425;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> coursesIds;
    private ArrayList<String> coursesNames;
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> coursesNames, ArrayList<String> coursesIds, Context context) {
        this.coursesIds = coursesIds;
        this.coursesNames = coursesNames;
        this.context = context;
     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("onCreateViewHolder", "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("onBindViewHolder", "onBindViewHolder: called.");
       holder.courseid.setText(coursesIds.get(position));
       holder.coursename.setText(coursesIds.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("Count", "items: called.");

        return coursesIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView courseid;
        TextView coursename;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             courseid = (TextView) itemView.findViewById(R.id.helloworld);
             coursename = (TextView) itemView.findViewById(R.id.course_name);
            Button button = itemView.findViewById(R.id.button);
            LinearLayout parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
