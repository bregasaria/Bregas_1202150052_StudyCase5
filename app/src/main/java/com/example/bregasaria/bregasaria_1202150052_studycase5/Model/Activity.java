package com.example.bregasaria.bregasaria_1202150052_studycase5.Model;

/**
 * Created by BREGAS on 3/25/2018.
 */

public class Activity {

    // Mendeklarasikan variabel
    private long id;
    private String todo;
    private String description;
    private String priority;

    public Activity(){

    }

    // Membuat konstruktor
    public Activity(String todo, String description, String priority){
        this.todo = todo;
        this.description = description;
        this.priority = priority;
    }

    // Membuat konstruktor
    public Activity(int id, String todo, String description, String priority){
        this.id = id;
        this.todo = todo;
        this.description = description;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
