package com.yawolart;


import com.yawolart.entity.Students;
import com.yawolart.repository.StudentRepository;
import com.yawolart.repository.StudentRepositoryBasic;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Main] amarjit");

        /*this is the basid crud methods*/
        StudentRepositoryBasic studentRepositoryBasic = new StudentRepositoryBasic();
        //studentRepositoryBasic.createStudentTable();
        //studentRepositoryBasic.addStudentBasic();
        //studentRepositoryBasic.readStudentBasic();
        //studentRepositoryBasic.updateBasic();
        //studentRepositoryBasic.deleteBasic();

        /*this is the proper crud methods*/
        StudentRepository studentRepository = new StudentRepository();

//        studentRepository.addStudent(
//            new Students("Amarjit","amarjit@yawolart.com",23)
//        );

        //studentRepository.readStudentById(2);

        //studentRepository.readAllStudent();

//        studentRepository.updateStudent(
//                new Students("amarjit", "amarjit@yawolart.com",24),
//                4
//        );

        //studentRepository.deleteStudent(4);
    }
}