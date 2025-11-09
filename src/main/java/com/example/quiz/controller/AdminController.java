package com.example.quiz.controller;

import com.example.quiz.model.Quiz;
import com.example.quiz.repository.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/quizzes")
public class AdminController {

    private final QuizRepository quizRepository;

    public AdminController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz saved = quizRepository.save(quiz);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> list() {
        return ResponseEntity.ok(quizRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> get(@PathVariable Long id) {
        return quizRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
