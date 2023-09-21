package com.example.jpastudentprep.config;

import com.example.jpastudentprep.model.Student;
import com.example.jpastudentprep.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        Student s1 = new Student();
        s1.setName("Bruce");
        s1.setBornDate(LocalDate.of(2010, 11, 12));
        s1.setBornTime(LocalTime.of(10, 11,12));
        studentRepository.save(s1); //insert
        s1.setName("Tim");
        studentRepository.save(s1); //update

        /*Student s2 = new Student();
        s2.setName("Dickens");
        s2.setBornDate(LocalDate.of(2010, 10, 10));
        s2.setBornTime(LocalTime.of(11, 11,9));
        studentRepository.save(s2);*/

    }
}
