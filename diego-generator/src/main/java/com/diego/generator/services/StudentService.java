package com.diego.generator.services;

import com.diego.generator.model.Student;
import com.diego.generator.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentGenerator studentGenerator;

    public StudentService(StudentRepository studentRepository,
                          StudentGenerator studentGenerator){
        this.studentRepository = studentRepository;
        this.studentGenerator = studentGenerator;
    }

    public Student generateStudent(){
        return studentRepository.save(studentGenerator.generateStudent());
    }

    public Iterable<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
