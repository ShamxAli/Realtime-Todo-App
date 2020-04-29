package com.startup.realtimetodoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.startup.realtimetodoapp.EditorActivity;
import com.startup.realtimetodoapp.R;
import com.startup.realtimetodoapp.Todo;

import java.util.List;

public class AdapterTodo extends RecyclerView.Adapter<AdapterTodo.MyViewHolder> {

    Context context;
    List<Todo> list;
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public AdapterTodo(Context context, List<Todo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Todo todo = list.get(position);
        holder.textView.setText(todo.getName() + " | " + todo.getAge());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String uid = todo.getUid();
                FirebaseDatabase.getInstance().getReference("Users").child(uid).removeValue();
                setPosition(holder.getAdapterPosition());
                return true;
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditorActivity.class);
                intent.putExtra("key", todo.getUid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, tvAge;
        ImageView button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.desgn_tv);
//            tvAge = itemView.findViewById(R.id.tvAge);
            button = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
