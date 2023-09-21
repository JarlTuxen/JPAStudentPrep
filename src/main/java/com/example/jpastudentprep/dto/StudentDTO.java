package com.example.jpastudentprep.dto;

import com.example.jpastudentprep.model.Student;

import java.time.LocalDate;
import java.time.LocalTime;

public record StudentDTO(int id, String name, LocalDate bornDate, LocalTime bornTime) {
    public Student toEntity() {
        return new Student(id(), name(), bornDate(), bornTime());
    }
}
