package com.university.entities;

import com.university.enums.Degree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double salary;
    private Degree degree;

    @ManyToMany(mappedBy = "lecturers")
    private List<Department> departments = new ArrayList<>();

    public Lecturer(String name, Double salary, Degree degree) {
        this.name = name;
        this.salary = salary;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return name;
    }
}
