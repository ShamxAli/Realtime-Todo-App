package com.startup.realtimetodoapp;

import androidx.annotation.Nullable;

public class Todo {

    String name;
    int age;
    String uid;  // for deleting and updating


    // Req for db
    public Todo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Todo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Todo) {
            Todo todo = (Todo) obj;
            return this.uid.equals(todo.getUid());
        } else {
            return false;
        }
    }
}
