package com.example.jpastudentprep.dto;

import com.example.jpastudentprep.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentConverterTest {

    @Autowired
    StudentConverter studentConverter;

    Student testStudent = new Student(
            42,
            "Neja",
            LocalDate.of(2005, 6, 7),
            LocalTime.of(8, 9,10)
    );
    StudentDTO testStudentDTO = new StudentDTO(
            42,
            "Neja",
            LocalDate.of(2005, 6, 7),
            LocalTime.of(8, 9,10)
    );

    @Test
    void toEntityTest() {
        //Act
        Student resultStudent = studentConverter.toEntity(testStudentDTO);
        //Assert
        assertEquals(resultStudent.getId(), testStudent.getId());
    }

    @Test
    void toDTOTest() {
        //Act
        StudentDTO resultStudentDTO = studentConverter.toDto(testStudent);
        //Assert
        assertEquals(testStudentDTO.id(), resultStudentDTO.id());
    }
}