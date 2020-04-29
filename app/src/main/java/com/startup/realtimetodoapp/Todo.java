package com.startup.realtimetodoapp;

public class Todo {

    String name;
    int age;


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
}
