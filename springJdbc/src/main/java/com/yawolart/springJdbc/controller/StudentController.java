package com.yawolart.springJdbc.controller;

import com.yawolart.springJdbc.model.Student;
import com.yawolart.springJdbc.repository.StudentRepository;
import com.yawolart.springJdbc.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/springjdbc")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> postController(
            @RequestBody Student student
    ){
        String respString = studentService.createStudentService(student);
        return ResponseEntity.ok(respString);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudentController()
    {
        List<Student> students = studentService.readAllStudentService();
        return ResponseEntity.ok(students);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Student> getStudentByIdController(
            @RequestParam long id
    ){
        Student student = new Student();
        student = studentService.readStudentByIdService(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<String> updateStudentController(
            @RequestBody Student student,
            @RequestParam long id
    ){
        String respString = studentService.updateStudentService(
                student,id
        );
        return ResponseEntity.ok(respString);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudentController(
            @RequestParam long id
    ){
        String respString = studentService.deleteStudentService(id);
        return  ResponseEntity.ok(respString);
    }

}
