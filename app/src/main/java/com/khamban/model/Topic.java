package com.khamban.model;

import java.io.Serializable;
import java.util.List;

public class Topic implements Serializable{
    private String question;
    private List<String> subName,point;

    public Topic(String question, List<String> subName) {
        this.question = question;
        this.subName = subName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getSubName() {
        return subName;
    }
    public List<String> getPoint() {
        return point;
    }

    public void setSubName(List<String> subName) {
        this.subName = subName;
    }
}
