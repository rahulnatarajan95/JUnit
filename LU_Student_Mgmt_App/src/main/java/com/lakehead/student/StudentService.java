package com.lakehead.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // defines it as service
public class StudentService {
    @Autowired // creates a bean called studentRepository
    private StudentRepository studentRepository;

    //this method retrieves all student records
    public Iterable<Student> retrieveAllStudents() {
        return studentRepository.findAll();
    }

    //this method retrieve a student based on id
    public Student retrieveStudent(int id) throws Exception {
        Optional<Student> studentById = studentRepository.findById(id);

        if (!studentById.isPresent())
            throw new Exception("Student id is not present");

        return studentById.get();
    }

    //this method is used to create a student record
    public ResponseEntity<Student> createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);

        /*URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();*/
        return new ResponseEntity<Student>(savedStudent, HttpStatus.OK);
    }

    //this method is used to update a student record
    public ResponseEntity<Object> updateStudent(Student student, int id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }

    //this method deletes a student record
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

}
