package com.example.todoapp;

public class Taskitem{

    String taskname1;
    String taskname2;

    String taskname3;

    int taskname4;


    public Taskitem(String taskname1, String taskname2, String taskname3, int taskname4) {
        this.taskname1 = taskname1;
        this.taskname2 = taskname2;
        this.taskname3 = taskname3;
        this.taskname4 = taskname4;
    }

    public String getTaskname3() {
        return taskname3;
    }

    public int getTaskname4() {
        return taskname4;
    }

    public void setTaskname3(String taskname3) {
        this.taskname3 = taskname3;
    }

    public void setTaskname4(int taskname4) {
        this.taskname4 = taskname4;
    }




    public String getTaskname1() {
        return taskname1;
    }

    public String getTaskname2() {
        return taskname2;
    }

    public void setTaskname1(String taskname1) {
        this.taskname1 = taskname1;
    }

    public void setTaskname2(String taskname2) {
        this.taskname2 = taskname2;
    }



}
