package com.example.q.cs496_prj1;

/**
 * Created by q on 2016-06-30.
 */
public class Todo {
    public String work;
    public String due_date;
    public boolean is_complete;

    String get_work() {return this.work;}
    String get_due_date() {return this.due_date;}
    boolean get_is_complete() {return this.is_complete;}
    void set_is_complete(boolean complete) {this.is_complete = complete;}

    public Todo (String work, String due) {
        this.work = work;
        this.due_date = due;
        this.is_complete = false;
    }
    public Todo (String work, String due, boolean complete) {
        this.work = work;
        this.due_date = due;
        this.is_complete = complete;
    }
}
