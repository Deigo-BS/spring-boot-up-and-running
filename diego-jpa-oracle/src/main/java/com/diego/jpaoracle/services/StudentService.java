package com.diego.jpaoracle.services;

import com.diego.jpaoracle.model.Student;
import com.diego.jpaoracle.proxies.StudentProxy;
import com.diego.jpaoracle.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentProxy studentProxy;
    private final StudentRepository studentRepository;

    public StudentService(StudentProxy studentProxy,
                          StudentRepository studentRepository){
        this.studentProxy = studentProxy;
        this.studentRepository = studentRepository;
    }

    public Student getStudent(){
        Student student = studentProxy.getStudent();
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            return studentRepository.save(student); // actualización
        } else {
            student.setId(null); // forzar inserción
            return studentRepository.save(student);
        }
    }

    public Iterable<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }
}
