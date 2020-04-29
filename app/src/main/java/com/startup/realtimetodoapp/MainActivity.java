package com.startup.realtimetodoapp;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.startup.realtimetodoapp.adapter.AdapterTodo;
import com.startup.realtimetodoapp.model.TODO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    List<TODO> list = new ArrayList<>();
    ;
    AdapterTodo adapterTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        showRecyclerView();
        // RealTime Database
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

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
