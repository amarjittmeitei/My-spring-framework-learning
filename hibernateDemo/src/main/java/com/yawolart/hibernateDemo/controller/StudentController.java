package com.yawolart.hibernateDemo.controller;

import com.yawolart.hibernateDemo.model.Student;
import com.yawolart.hibernateDemo.service.StudentService;
import org.hibernate.sql.results.internal.ResolvedSqlSelection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hibernate")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<String> postController(
            @RequestBody
            Student student
    ){
        studentService.createStudentService(student);
        return ResponseEntity.ok("Post DONE");
    }

    @GetMapping
    public ResponseEntity<Student> getController(
            @RequestParam
            Long id
    ){
        Student student = studentService.readStudentService(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<String> putController(
            @RequestBody
            Student student,
            @RequestParam
            Long id
    ){
        studentService.updateStudentService(student,id);
        return ResponseEntity.ok("Put DONE");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteController(
            @RequestParam
            Long id
    ){
        studentService.deleteStudentService(id);
        return ResponseEntity.ok("delete DONE");
    }
}
