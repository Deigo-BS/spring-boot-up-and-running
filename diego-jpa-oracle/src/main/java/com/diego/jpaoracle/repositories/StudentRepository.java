package com.diego.jpaoracle.repositories;

import com.diego.jpaoracle.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
