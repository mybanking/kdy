package com.bugcoder.sc.student;

import java.io.Serializable;

public class Course implements Serializable {
    public Course(String major, String courseName, String teacherName, int taskNum, int myProgress) {
        this.major = major;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.taskNum = taskNum;
        this.myProgress = myProgress;
    }

    ;
    public String major;
    public String courseName;
    public String teacherName;
    public int taskNum = 0;
    public int myProgress = 0;
}
