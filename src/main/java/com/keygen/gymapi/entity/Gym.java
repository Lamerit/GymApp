package com.keygen.gymapi.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "gyms")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String location;

    // Opsiyonel: bu spor salonundaki kullanıcıları listelemek istersen
    @OneToMany(mappedBy = "gym")
    private List<User> members;

    // ─── getters & setters ─────────────────
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public List<User> getMembers() { return members; }
    public void setMembers(List<User> members) { this.members = members; }

    public void setId(Integer gymId) {

    }
}
