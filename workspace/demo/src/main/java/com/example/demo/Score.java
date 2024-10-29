package com.example.demo;


public class Score {
    private String type;
    private int score;

    public Score() {
    }
    public Score(String type, int score) {
        this.type = type;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
