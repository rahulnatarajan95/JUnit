package com.lakehead.student;

import org.springframework.data.repository.CrudRepository;

//Spring will create a Bean called studentRepository that can hold student records

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
