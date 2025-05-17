package com.keygen.gymapi.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String specialty;

    @OneToMany(mappedBy = "coach")
    private List<User> trainees;

    // ─── getters & setters ─────────────────
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public List<User> getTrainees() { return trainees; }
    public void setTrainees(List<User> trainees) { this.trainees = trainees; }

    public void setId(Integer coachId) {
    }
}
