package com.example.jpastudentprep.repository;

import com.example.jpastudentprep.model.Student;
//import org.hamcrest.collection.IsIterableContainingInAnyOrder.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void init(){
        Student testStudent1 = new Student(
                42,
                "Tim",
                LocalDate.of(2005, 6, 7),
                LocalTime.of(8, 9,10));
        Student testStudent2 = new Student(
                43,
                "Susanne",
                LocalDate.of(2015, 6, 7),
                LocalTime.of(18, 9,10));
        studentRepository.save(testStudent1);
        studentRepository.save(testStudent2);
    }

    @Test
    void testOneTime(){
        List<Student> lst = studentRepository.findAllByName("Tim");
        assertEquals(1, lst.size());
        assertEquals("Tim", lst.get(0).getName());
    }

    @Test
    void getStudents(){
        List<Student> studentList = studentRepository.findAll();
        assertEquals(2, studentList.size());
        //collection contains elements with name in any order
        assertThat(studentList, containsInAnyOrder(hasProperty("name",  is("Tim")), hasProperty("name", is("Susanne"))));
    }
}