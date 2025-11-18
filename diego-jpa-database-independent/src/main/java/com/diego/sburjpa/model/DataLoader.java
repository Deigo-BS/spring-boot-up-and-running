package com.diego.sburjpa.model;

import com.diego.sburjpa.repositories.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final StudentRepository studentRepository;

    public DataLoader(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void loadData(){
        studentRepository.deleteAll();
        studentRepository.save(new Student("Diego", "Brito"));
    }
}
