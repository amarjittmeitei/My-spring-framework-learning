package com.yawolart.hibernateDemo.service;

import com.yawolart.hibernateDemo.model.Student;
import com.yawolart.hibernateDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    //create
    @Transactional
    public void createStudentService(Student student){
        studentRepository.save(student);
    }

    //read
    public Student readStudentService(Long id){
        return studentRepository.findById(id);
    }

    //update
    @Transactional
    public void updateStudentService(Student student, Long id){
        Student readStudent = studentRepository.findById(id);

        if(student == null){
            throw new RuntimeException("data not found from db");
        }

        readStudent.setName(student.getName());
        readStudent.setEmail(student.getEmail());
        readStudent.setAge(student.getAge());
        //here to method to update the database will be called
        //but, still it will update the database as it is a transactional code
        //see note v29 for more information
    }

    //delete
    @Transactional
    public void deleteStudentService(Long id)
    {
        Student student = studentRepository.findById(id);
        if(student == null) {
            throw new RuntimeException("data not found from the db");
        }
        studentRepository.removeStudent(student);
    }
}
