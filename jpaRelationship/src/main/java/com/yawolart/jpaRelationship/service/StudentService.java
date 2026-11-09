package com.yawolart.jpaRelationship.service;

import com.yawolart.jpaRelationship.model.Department;
import com.yawolart.jpaRelationship.model.Profile;
import com.yawolart.jpaRelationship.model.Student;
import com.yawolart.jpaRelationship.respository.DepartmentRepository;
import com.yawolart.jpaRelationship.respository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void createStudent(
            Student student,
            Long deptId,
            String bio
    ){
        Department department = departmentRepository.findById(deptId);
        student.setDepartment(department);

        Profile profile = new Profile();
        profile.setBio(bio);
        profile.setStudent(student);
        student.setProfile(profile);

        studentRepository.save(student);
    }

    public Student findStudent(Long id){
        return studentRepository.findById(id);
    }

    @Transactional
    public void removeStudent(Long id){
        Student student = studentRepository.findById(id);
        studentRepository.removeStudent(student);
    }
}
