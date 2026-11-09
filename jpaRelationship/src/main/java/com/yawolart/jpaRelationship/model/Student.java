package com.yawolart.jpaRelationship.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    //quick fix from circular dependency loop on get call
    //best and professional practice is DTO
    @JsonIgnoreProperties("students")
    private Department department;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties
    private Profile profile;
}
