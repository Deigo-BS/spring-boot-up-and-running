package com.diego.sburjpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;

    public Student (String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }
}
