package com.startup.realtimetodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditorActivity extends AppCompatActivity {

    EditText editName, editAge;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editName = findViewById(R.id.et_text);
        editAge = findViewById(R.id.et_age);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Users");
    }

    // Writing data to firebase
    public void btnSave(View view) {
        String name = editName.getText().toString();
        int age = Integer.parseInt(editAge.getText().toString());
        Todo todo = new Todo(name, age);
        mRef.push().setValue(todo);
        finish();

    }
}