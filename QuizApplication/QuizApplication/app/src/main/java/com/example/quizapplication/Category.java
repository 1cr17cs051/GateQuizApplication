package com.example.quizapplication;

import androidx.annotation.NonNull;

public class Category {
    public static final int PROGRAMMING_AND_DATASTRUCTURES = 1;
    public static final int OPERATING_SYSTEMS = 2;
    public static final int AUTOMATA = 3;
    public static final int DBMS =4;
    public static final int DIGITAL_LOGIC = 5;
    public static final int AMPTITUDE = 6;
    public static final int VERBAL = 7;
    public static final int LOGICAL = 8;

    private int id;
    private String name;

    public Category(){
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
