package com.university.entities;

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
public class Department {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private Integer salary;
    private Integer employee_count;
    private Long headLecturerId;
    @OneToOne
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Lecturer> lecturers = new ArrayList<>();

    public void addLecturer(Lecturer lecturer){
        lecturers.add(lecturer);
        lecturer.getDepartments().add(this);
    }

    public void deleteLecturer(Lecturer lecturer){
        lecturers.remove(lecturer);
        lecturer.getDepartments().remove(this);
    }


}
