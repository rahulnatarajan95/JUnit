package com.lakehead.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // It defines that this class is a Controller
@RequestMapping(path="/LU") //URL's will start with /LU
public class StudentController {

    @Autowired // creates a bean called studentService
    private StudentService studentService;

    //this get method returns the list of students
    @GetMapping("/students")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentService.retrieveAllStudents();
    }

    //this get method return the student's record based on id
    @GetMapping("/student/{id}")
    public @ResponseBody Student retrieveStudent(@PathVariable int id) throws Exception {
        return studentService.retrieveStudent(id);
    }

    //this method is used to create a new student record
    @PostMapping("/addStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);

    }

    //this method is used to edit a student record based on id
    @PutMapping("/student/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable int id) {
        return studentService.updateStudent(student, id);
    }

    //this method deletes a student record based on id
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

}
