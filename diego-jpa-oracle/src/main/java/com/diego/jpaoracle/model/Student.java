package com.diego.jpaoracle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq") //este 'generator=' debe coincidir con 'name=' de @SequenceGenerator
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1) //'sequenceName=' es el nombre de la secuencia en Oracle datbase
    private Long id;
    private String name;
    private String lastName;
}
