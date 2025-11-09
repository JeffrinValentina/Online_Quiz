package com.example.quiz.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Submission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentUsername;
    private Long quizId;
    private Integer totalScore;

    @ElementCollection
    private Map<Long,Integer> answers = new HashMap<>(); // questionId -> selectedChoiceIndex

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }
    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }
    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
    public Map<Long,Integer> getAnswers() { return answers; }
    public void setAnswers(Map<Long,Integer> answers) { this.answers = answers; }
}
