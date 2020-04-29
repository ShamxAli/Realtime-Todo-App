package com.startup.realtimetodoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.startup.realtimetodoapp.adapter.AdapterTodo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase mDatabase;
    int position;
    DatabaseReference mRef;
    List<Todo> list;
    ChildEventListener childEventListener;
    AdapterTodo adapterTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        list = new ArrayList<>();
        // RealTime Database
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Users");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                Todo todo = dataSnapshot.getValue(Todo.class);
                todo.setUid(key); // setting the key for update and delete
                Log.d("mytag", "onChildAdded: called");
                list.add(todo);
                adapterTodo.notifyItemRemoved(adapterTodo.getPosition());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Todo todo = dataSnapshot.getValue(Todo.class);
                todo.setUid(dataSnapshot.getKey());
                list.remove(todo);
                Log.d("mytag", "onChildChangedddddddddd: called");
                list.add(todo);
                adapterTodo.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Todo todo = dataSnapshot.getValue(Todo.class);
                todo.setUid(dataSnapshot.getKey());
                list.remove(todo);
                adapterTodo.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mRef.addChildEventListener(childEventListener);
        showRecyclerView();
    }

    // Go to next activity
    public void openEditor(View view) {
        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
        startActivity(intent);
    }

    void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

    }

    void showRecyclerView() {
        adapterTodo = new AdapterTodo(this, list);
        recyclerView.setAdapter(adapterTodo);
    }

}
