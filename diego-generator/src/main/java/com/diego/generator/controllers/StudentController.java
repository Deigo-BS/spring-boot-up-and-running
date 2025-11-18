package com.diego.generator.controllers;

import com.diego.generator.model.Student;
import com.diego.generator.services.StudentService;
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
        return studentService.generateStudent();
    }

    @GetMapping("/all")
    public Iterable<Student> getAllStudent(){
        return studentService.getAllStudents();
    }
}
