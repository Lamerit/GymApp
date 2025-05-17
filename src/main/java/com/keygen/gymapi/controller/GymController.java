package com.keygen.gymapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.keygen.gymapi.entity.Gym;
import com.keygen.gymapi.repository.GymRepository;
import java.util.List;

@RestController
@RequestMapping("/api/gyms")
public class GymController {

    @Autowired
    private GymRepository gymRepo;

    @GetMapping
    public ResponseEntity<List<Gym>> list() {
        return ResponseEntity.ok(gymRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<Gym> create(@RequestBody Gym gym) {
        return ResponseEntity.ok(gymRepo.save(gym));
    }

}