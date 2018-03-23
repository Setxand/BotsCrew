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
    private Long id;
    private String name;
    private Long headLecturerId;


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

    @Override
    public String toString() {
        return name;
    }
}
