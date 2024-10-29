package com.example.demo;

// create Student document for MongoDB  database = node_express_db
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {
    @Id
    private int _id;
    private String name;
    private Score[] scores;
    public  Student() {
    }

    public Student(String name, Score[] scores) {
        this.name = name;
        this.scores = scores;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public Score[] getScores() {
        return scores;
    }

    public void setId(int id) {
        this._id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScores(Score[] scores) {
        this.scores = scores;
    }

    public String toString() {
        return "Student [id=" + _id + ", name=" + name + ", scores=" + scores + "]";
    }

    public String getScoresString() {
        String result = "";
        for (Score score : scores) {
            result += score.getType() + ": " + score.getScore() + ", ";
        }
        return result;
    }

    public int getScore(String subject) {
        for (Score score : scores) {
            if (score.getType().equals(subject)) {
                return score.getScore();
            }
        }
        return -1;
    }

    public void setScore(String subject, int score) {
        for (Score s : scores) {
            if (s.getType().equals(subject)) {
                s.setScore(score);
            }
        }
    }


}



