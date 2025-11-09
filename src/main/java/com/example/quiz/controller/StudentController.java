package com.example.quiz.controller;

import com.example.quiz.model.*;
import com.example.quiz.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final QuizRepository quizRepository;
    private final SubmissionRepository submissionRepository;

    public StudentController(QuizRepository quizRepository, SubmissionRepository submissionRepository) {
        this.quizRepository = quizRepository;
        this.submissionRepository = submissionRepository;
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<Quiz>> list() {
        return ResponseEntity.ok(quizRepository.findAll());
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> get(@PathVariable Long id) {
        return quizRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/quizzes/{id}/submit")
    public ResponseEntity<Submission> submit(@PathVariable Long id,
                                             @RequestBody Map<Long,Integer> answers,
                                             Principal principal) {
        // compute score
        Optional<Quiz> oq = quizRepository.findById(id);
        if (oq.isEmpty()) return ResponseEntity.notFound().build();
        Quiz quiz = oq.get();
        int total = 0;
        Map<Long,Integer> answerMap = new HashMap<>();
        for (var q : quiz.getQuestions()) {
            Integer selected = answers.get(q.getId());
            if (selected != null) {
                answerMap.put(q.getId(), selected);
                if (selected.equals(q.getCorrectIndex())) total += q.getMarks();
            }
        }
        Submission s = new Submission();
        s.setQuizId(id);
        s.setStudentUsername(principal.getName());
        s.setAnswers(answerMap);
        s.setTotalScore(total);
        submissionRepository.save(s);
        return ResponseEntity.ok(s);
    }
}
