package com.example.jpastudentprep.controller;

import com.example.jpastudentprep.dto.StudentConverter;
import com.example.jpastudentprep.dto.StudentDTO;
import com.example.jpastudentprep.model.Student;
import com.example.jpastudentprep.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class StudentRestController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentConverter studentConverter;

    @GetMapping("/students")
    public List<StudentDTO> students() {
       List<Student> studentList = studentRepository.findAll();
       List<StudentDTO> studentDTOList = new ArrayList<>();
       studentList.forEach(student -> {
           //StudentDTO studentDTO = studentConverter.toDTO(student);
           studentDTOList.add(studentConverter.toDTO(student));
       });
       return studentDTOList;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") int id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            /*StudentDTO studentDTO = new StudentDTO(
                    student.getId(),
                    student.getName(),
                    student.getBornDate(),
                    student.getBornTime()
            );*/
            return ResponseEntity.ok(studentConverter.toDTO(student));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO postStudent(@RequestBody StudentDTO studentDTO){
        System.out.println(studentDTO);
        Student student = studentConverter.toEntity(studentDTO);
        student.setId(0);
        studentRepository.save(student);
        return studentConverter.toDTO(student);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentDTO> putStudent(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student student = studentDTO.toEntity();
            student.setId(id);
            studentRepository.save(student);
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

    @GetMapping("/addstudent")
    public List<Student> addStudents() {
        Student std = new Student();
        std.setName("Qrt");
        std.setBornDate(LocalDate.now());
        std.setBornTime(LocalTime.now());
        studentRepository.save(std);

        List<Student> lst = studentRepository.findAll();
        return lst;
    }

    @GetMapping("students/{name}")
    public List<StudentDTO> getAllStudentsByName(@PathVariable String name){
        List<Student> studentList = studentRepository.findAllByName(name);
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentList.forEach(student -> {
            StudentDTO studentDTO = new StudentDTO(
                    student.getId(),
                    student.getName(),
                    student.getBornDate(),
                    student.getBornTime()
            );
            studentDTOList.add(studentDTO);
        });
        return studentDTOList;
    }
}
