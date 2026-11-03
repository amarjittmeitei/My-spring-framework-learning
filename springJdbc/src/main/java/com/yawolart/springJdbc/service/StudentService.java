package com.yawolart.springJdbc.service;

import com.yawolart.springJdbc.model.Student;
import com.yawolart.springJdbc.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String createStudentService(Student student){
        return studentRepository.createStudent(student);
    }

    public Student readStudentByIdService(long id)
    {
        return studentRepository.readStudentById(id);
    }

    public List<Student> readAllStudentService(){
        return studentRepository.readAllStudent();
    }

    public String updateStudentService(Student student, long id){
        return studentRepository.updateStudent(student,id);
    }

    public String deleteStudentService(long id){
        return studentRepository.deleteStudentById(id);
    }
}
