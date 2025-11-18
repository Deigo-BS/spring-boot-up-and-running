package com.diego.sburjpa.services;

import com.diego.sburjpa.model.Student;
import com.diego.sburjpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentGenerator studentGenerator;

    public StudentService(StudentRepository studentRepository,
                          StudentGenerator studentGenerator){
        this.studentRepository = studentRepository;
        this.studentGenerator = studentGenerator;
    }

    @Transactional
    public Student generateAndSaveStudent(){
        return studentRepository.save(studentGenerator.generateStudent());
    }

    public Iterable<Student> getAllStudents(){
        return studentRepository.findAll();
    }

}

