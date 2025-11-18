package com.diego.sburjpa.controllers;

import com.diego.sburjpa.model.Student;
import com.diego.sburjpa.services.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public Student generateAndSaveStudent(){
        return studentService.generateAndSaveStudent();
    }

    @GetMapping("/all")
    public Iterable<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

}
