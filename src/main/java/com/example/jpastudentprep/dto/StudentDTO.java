package com.example.jpastudentprep.dto;

import com.example.jpastudentprep.model.Student;

import java.time.LocalDate;
import java.time.LocalTime;

//immutable DTO
public record StudentDTO(int id, String name, LocalDate bornDate, LocalTime bornTime) {
    /* alternativ placering af toEntity, hvis det kun er simple konverteringer
    public Student toEntity() {
        return new Student(id(), name(), bornDate(), bornTime());
    }*/
}
