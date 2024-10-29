package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepo extends MongoRepository<Student,Integer> {
    // get total count of students who have scored more than 90 in a given subject
    public long countByScoresTypeAndScoresScoreGreaterThan(String type, int score);

    // get list of students who have scored more than 90 in a given type using Query
    @Query("{ 'scores.type' : ?0, 'scores.score' : { $gt : ?1 } }")
    public List<Student> findByScoresTypeAndScoresScoreGreaterThan(String type, int score);


    @Query(value = "{ 'scores': { $elemMatch: { score: { $gt: 80 }, type: 'quiz' } } }", fields = "{ 'name': 1}")
    List<String> findStudentsWithHighQuizScores();





    //public List<Student> findByScoresTypeAndScoresScoreGreaterThan(String type, int score);
}
