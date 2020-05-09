package com.example.cs425.recyclerview;

import android.content.Context;
import android.graphics.Color;
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
    ArrayList<String> assignmentsDescription = new ArrayList<>();
    ArrayList<String> assignmentsDueDate = new ArrayList<>();
    ArrayList<String> assignmentsStatus = new ArrayList<>();

    Context context;

    //private OnAssignmentListener onAssignmentListener;


    public AssignmentRecyclerViewAdapter(Context context, ArrayList<String> assignmentsName, ArrayList<String> assignmentsDescription
            , ArrayList<String> assignmentsDueDate, ArrayList<String> assignmentsStatus) {
        this.assignmentsName = assignmentsName;
        this.assignmentsDescription = assignmentsDescription;
        this.assignmentsDueDate = assignmentsDueDate;
        this.assignmentsStatus = assignmentsStatus;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_recycler_items,parent,false);
        AssignmentViewHolder viewHolder = new AssignmentViewHolder(view/*, onAssignmentListener*/);
        return  viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {

        holder.description.setVisibility(View.GONE);
        holder.dueDate.setVisibility(View.GONE);
        holder.status.setVisibility(View.GONE);

        holder.assignmentName.setText(assignmentsName.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.description.setText(assignmentsDescription.get(position));
                holder.dueDate.setText(assignmentsDueDate.get(position));
                holder.status.setText(assignmentsStatus.get(position));
                if (assignmentsStatus.get(position) == "Pending"){
                    holder.status.setBackgroundColor(Color.parseColor("#FF0000"));
                }else {
                    holder.status.setBackgroundColor(Color.parseColor("#2ECC71"));
                }

                holder.description.setVisibility(View.VISIBLE);
                holder.dueDate.setVisibility(View.VISIBLE);
                holder.status.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentsName.size();
    }


    public class AssignmentViewHolder extends RecyclerView.ViewHolder {

        TextView assignmentName;
        TextView description;
        TextView dueDate;
        TextView status;

        //OnAssignmentListener onAssignmentListener;


        public AssignmentViewHolder(@NonNull View itemView /*, OnAssignmentListener onAssignmentListener*/) {
            super(itemView);

            assignmentName = (TextView) itemView.findViewById(R.id.assignmentName);
            description = (TextView) itemView.findViewById(R.id.description);
            dueDate = (TextView) itemView.findViewById(R.id.duedate);
            status = (TextView) itemView.findViewById(R.id.status);

            //this.onAssignmentListener= onAssignmentListener;
            //itemView.setOnClickListener(this);
        }
    }
}

      /*  @Override
        public void onClick(View v) {
            onAssignmentListener.OnAssignmentClick(getAdapterPosition());
        }
    }

    public interface OnAssignmentListener{
        void OnAssignmentClick(int position);
    }*/

