package com.diego.sburjpa.services;


import com.diego.sburjpa.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class StudentGenerator {
    private final Random random = new Random();

    private List<String> studentName = List.of(
            "Alejandro", "Alicia", "Amanda",
            "Baldomero", "Baltasar", "Brandon",
            "Carlos", "Camilo", "Catalina",
            "Diego", "Daniela", "Dafne");

    private List<String> studentLastName = List.of(
            "Acosta", "Aguilar", "Alvarado",
            "Barrera", "Benavides", "Bravo",
            "Castillo", "Castro", "Cruz",
            "Escalante", "Esquivel", "Flores"
    );

    public Student generateStudent(){
        Student student = new Student();
        student.setName(studentName.get(random.nextInt(12)));
        student.setLastName(studentLastName.get(random.nextInt(12)));
        return student;
    }

}

