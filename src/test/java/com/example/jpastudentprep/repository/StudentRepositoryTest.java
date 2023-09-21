package com.example.jpastudentprep.repository;

import com.example.jpastudentprep.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void testOneTime(){
        List<Student> lst = studentRepository.findAllByName("Tim");
        assertEquals(1, lst.size());
    }

}