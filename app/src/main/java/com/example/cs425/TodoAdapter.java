package com.example.cs425;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs425.models.CourseInfo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter{
private Context context;
private List<CourseInfo> courseInfoList ;

    public TodoAdapter(Context context, List<CourseInfo> courseInfoList) {
        this.context = context;
        this.courseInfoList = courseInfoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_todo,null);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder{



        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
