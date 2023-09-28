package com.example.jpastudentprep.service;

import com.example.jpastudentprep.dto.StudentConverter;
import com.example.jpastudentprep.dto.StudentDTO;
import com.example.jpastudentprep.exception.StudentNotFoundException;
import com.example.jpastudentprep.model.Student;
import com.example.jpastudentprep.repository.StudentRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentConverter studentConverter;

    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable closable;

    //studentService kan ikke autowires, men skal laves manuelt med nødvendige services
    StudentService studentService;

    @BeforeEach
    void init(){
        Student s1 = new Student(
                1,
                "Kurt",
                LocalDate.of(2010, 11, 12),
                LocalTime.of(10, 11,12)
        );
        Student s2 = new Student(
                2,
                "Ida",
                LocalDate.of(1980, 1, 14),
                LocalTime.of(14, 15,16)
        );

        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);

        //initialize mock
        closable = MockitoAnnotations.openMocks(this);

        Mockito.when(studentRepository.findAll()).thenReturn(studentList);
        Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(s1));
        Mockito.when(studentRepository.findById(42)).thenReturn(Optional.empty());

        studentService = new StudentService(studentRepository, studentConverter);
    }

    @AfterEach
    void closeMock() throws Exception {
        //cleanup after each test
        closable.close();
    }

    @Test
    void getAllStudents() {
        List<StudentDTO> studentDTOList = studentService.getAllStudents();
        //skal bruge en del ekstra kode for at bruge hamcrest matcher, så i stedet simpelt opslag i listen
        assertEquals("Kurt", studentDTOList.get(0).name());
        assertEquals("Ida", studentDTOList.get(1).name());
    }

    @Test
    void getStudentById() {
        StudentDTO studentDTO = studentService.getStudentById(1);
        assertEquals("Kurt", studentDTO.name());
        assertThrows(StudentNotFoundException.class, ()-> studentService.getStudentById(42));
    }

    @Test
    void createStudent() {
        //Test that a StudentDTO with a student returns a StudentDTO with id given in Mock
    }

    @Test
    void updateStudent() {
        //Test that a StudentDTO and id results in change given in DTO
    }

    @Test
    void deleteStudentById() {
        //Test that a exception is thrown when deleting a non-existing student
    }
}