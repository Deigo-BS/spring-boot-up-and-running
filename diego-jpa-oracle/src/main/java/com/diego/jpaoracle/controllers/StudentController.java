package com.diego.jpaoracle.controllers;

import com.diego.jpaoracle.model.Student;
import com.diego.jpaoracle.services.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public Student getStudent(){
        return studentService.getStudent();
    }

    @GetMapping("/all")
    public Iterable<Student> getAllStudent(){
        return studentService.getAllStudents();
    }

    @DeleteMapping("/deleteall")
    public void deleteAllStudents(){
        studentService.deleteAllStudents();
    }

}
