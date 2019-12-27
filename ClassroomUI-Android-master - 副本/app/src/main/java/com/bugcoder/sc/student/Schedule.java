package com.bugcoder.sc.student;

import java.io.Serializable;

public class Schedule implements Serializable {
    public Schedule(String courseName, String teacherName, String time, String room, String date) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.time = time;
        this.room = room;
        this.date = date;
    }

    String courseName;
    String teacherName;
    String time;
    String room;
    String date;
}
