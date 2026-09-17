package com.resourceallocator.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "technologies")
@Getter
@Setter
@NoArgsConstructor
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String category;

    private String version;

    @Column(length = 2000)
    private String description;

    public Technology(String name, String category, String version, String description) {
        this.name = name;
        this.category = category;
        this.version = version;
        this.description = description;
    }
}