package com.startup.realtimetodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditorActivity extends AppCompatActivity {

    EditText editName, editAge;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editName = findViewById(R.id.et_text);
        editAge = findViewById(R.id.et_age);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Users");
        data = getIntent().getStringExtra("key"); // getting key for update
    }

    // Writing data to firebase
    public void btnSave(View view) {
        if (data != null) {

            String name = editName.getText().toString();
            int age = Integer.parseInt(editAge.getText().toString());
            Todo todo = new Todo(name, age);
            FirebaseDatabase.getInstance().getReference("Users").child(data).setValue(todo);
            finish();
        } else {
            String name = editName.getText().toString();
            int age = Integer.parseInt(editAge.getText().toString());
            Todo todo = new Todo(name, age);
            mRef.push().setValue(todo);
            finish();
        }

    }
}