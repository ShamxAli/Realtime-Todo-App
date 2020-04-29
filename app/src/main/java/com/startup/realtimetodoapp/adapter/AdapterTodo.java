package com.startup.realtimetodoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.startup.realtimetodoapp.R;
import com.startup.realtimetodoapp.Todo;

import java.util.List;

public class AdapterTodo extends RecyclerView.Adapter<AdapterTodo.MyViewHolder> {

    Context context;
    List<Todo> list;


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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Todo todo = list.get(position);
        holder.textView.setText(todo.getName() + " | " + todo.getAge());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, tvAge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.desgn_tv);
            tvAge = itemView.findViewById(R.id.tvAge);
        }
    }
}
