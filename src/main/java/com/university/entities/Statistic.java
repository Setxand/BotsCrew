package com.university.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Statistic {
    @Id
    @GeneratedValue
    private Long id;
    private Integer assistantsCount;
    private Integer associateProfessorsCount;
    private Integer professorsCount;

    public Statistic() {
        assistantsCount =0;
        associateProfessorsCount = 0;
        professorsCount = 0;
    }

    @Override
    public String toString() {
        return  "assistants - "+assistantsCount+"\n" +
                "associate professors - "+associateProfessorsCount+"\n" +
                "professors - "+ professorsCount+"\n\n";
    }
}
