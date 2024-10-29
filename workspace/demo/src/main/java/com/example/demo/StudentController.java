package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    // GET //students endpoint to get return List<Student> from MongoDB
    @GetMapping
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    // get //students/{id} endpoint to get a single Student from MongoDB
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        return studentRepo.findById(id).orElse(null);
    }

    // get //students/{type}/{score} endpoint to get List<Student> from MongoDB
    @GetMapping("/{type}/{score}")
    public List<Student> getStudentsByScore(@PathVariable("type") String type, @PathVariable("score") int score) {
        return studentRepo.findByScoresTypeAndScoresScoreGreaterThan(type, score);

    }
    @GetMapping("/high-scores")
    public List<String> getStudentsWithHighQuizScores() {
        return studentRepo.findStudentsWithHighQuizScores();
    }




}
