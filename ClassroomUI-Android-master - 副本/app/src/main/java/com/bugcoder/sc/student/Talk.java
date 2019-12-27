package com.bugcoder.sc.student;

import java.io.Serializable;
public class Talk implements Serializable {
    private  String stuId;
    private  String stuName;
    public Talk(String stuId, String stuName, String question, String date) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.question = question;
        this.date = date;
    }

    private String question;
    private String date;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




}
