package com.yawolart.jpaRelationship.controller;

import com.yawolart.jpaRelationship.model.Student;
import com.yawolart.jpaRelationship.respository.StudentRepository;
import com.yawolart.jpaRelationship.service.StudentService;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/{deptId}")
    public ResponseEntity<String> studentPostController(
            @RequestBody
            Student student,

            @PathVariable
            Long deptId,

            @RequestParam
            String bio
    ){
        studentService.createStudent(student,deptId,bio);
        return ResponseEntity.ok("DONE");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> studentGetController(
            @PathVariable
            Long id
    ){
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> studentDeleteController(
            @PathVariable
            Long id
    ){
        studentService.removeStudent(id);
        return ResponseEntity.ok("DONE");
    }

}
