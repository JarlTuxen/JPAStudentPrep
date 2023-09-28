package com.example.jpastudentprep.service;

import com.example.jpastudentprep.dto.StudentConverter;
import com.example.jpastudentprep.dto.StudentDTO;
import com.example.jpastudentprep.exception.StudentNotFoundException;
import com.example.jpastudentprep.model.Student;
import com.example.jpastudentprep.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentConverter studentConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentConverter::toDto)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return studentConverter.toDto(student.get());
        } else {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student studentToSave = studentConverter.toEntity(studentDTO);
        //ensure it's a create
        studentToSave.setId(0);
        Student savedStudent = studentRepository.save(studentToSave);
        return studentConverter.toDto(savedStudent);
    }

    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            Student updatedStudent = studentConverter.toEntity(studentDTO);
            //ensure it's the student by path id that is updated
            updatedStudent.setId(id);
            Student savedStudent = studentRepository.save(updatedStudent);
            return studentConverter.toDto(savedStudent);
        } else {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
    }

    public void deleteStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
    }
}

