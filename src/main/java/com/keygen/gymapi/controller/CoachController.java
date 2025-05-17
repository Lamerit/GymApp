package com.keygen.gymapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.keygen.gymapi.entity.Coach;
import com.keygen.gymapi.repository.CoachRepository;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    @Autowired
    private CoachRepository coachRepo;

    @GetMapping
    public ResponseEntity<List<Coach>> list() {
        return ResponseEntity.ok(coachRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<Coach> create(@RequestBody Coach coach) {
        return ResponseEntity.ok(coachRepo.save(coach));
    }
}
