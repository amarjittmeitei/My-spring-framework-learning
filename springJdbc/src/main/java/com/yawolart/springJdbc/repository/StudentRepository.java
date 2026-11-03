package com.yawolart.springJdbc.repository;

import com.yawolart.springJdbc.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    /*
        in application.properties put:
            spring.datasource.url = url_here
            spring.datasource.username = username_here
            spring.datasource.password = password_here
    */


    /*
    //DataSource automatically get the connection credential from
    //application.properties automatically

    @Autowired
    DataSource dataSource;

    //Creating connection using DataSource that use connection credential
    //from application.properties
    @PostConstruct
    public void init()
    {
        try
        {
            Connection connection = dataSource.getConnection();
            System.out.println("[StudentRepository] Database connection check successful");
        }catch (SQLException ex){
            System.out.println("[StudentRepository] Cannot connect to database");
        }
    }
    */

    //Here we will use JdbcTemplate use DataSource inside and allow use to call
    //  update() -> for CREATE,UPDATE,DELETE SQL queries.
    //  query() -> for read (SELECT)
    //  queryForObject() -> for read one row

    JdbcTemplate jdbcTemplate;

    //this object is to be provided to the jdbcTemplate
    StudentRowMapper studentRowMapper = new StudentRowMapper();

    //for simple mapping when the database attribute name and java
    //object name are same or canonical-form and cemalCase
    //we can use BeanPropertyRowMapping object as follows
    // --to use this comment the above studentRowMapper object
//    RowMapper<Student> studentRowMapper =
//            new BeanPropertyRowMapper<>(Student.class);

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    //to create table using
    //we will not use it here
    //for this program we will work on an existing table
    @PostConstruct
    public void createTable()
    {
        String sql = """
             CREATE TABLE studentTry (
                 id INT PRIMARY KEY AUTO_INCREMENT,
                 name VARCHAR(100) NOT NULL,
                 email VARCHAR(100),
                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
             )
             """;
        try {
            jdbcTemplate.execute(sql);
            System.out.println("[StudentRepository] Create table check successful");
        }catch (Exception ex){
            System.out.println("[StudentRepository] " + ex.getMessage());
        }

    }
    */

    //C - create
    public String createStudent(Student student){
        String sql = """
                INSERT INTO students(name,email,age) 
                VALUES(?,?,?)
                """;
        int result = jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail(),
                student.getAge()
        );
        if(result <= 0){
            System.out.println("[StudentRepository] createStudent failed!");
            return "something  went wrong";
        }
        System.out.println("[StudentRepository] createStudent: " +
                "Row affected: " + result);
        return "Row affected: " + result;

    }

    //R - Read
    public Student readStudentById(long id)
    {
        String sql = """
                SELECT id,name,email,age 
                FROM students 
                WHERE id = ?
                """;
        Student student = new Student();
        student = jdbcTemplate.queryForObject(
                sql,
                studentRowMapper,
                id
        );
        System.out.println("[StudentRepository] readStudentById");
        return student;
    }

    //ReadAll
    public List<Student> readAllStudent(){
        String sql = """
                SELECT id,name,email,age 
                FROM students
                """;
        List<Student> students = new ArrayList<>();
        students = jdbcTemplate.query(
                sql,
                new StudentRowMapper()
                );
        System.out.println("[StudentRepository] createStudent");
        return students;
    }

    //U - update
    public String updateStudent(Student student,long id){
        String sql = """
                UPDATE students 
                SET name = ?, email = ?, age = ? 
                WHERE id = ?
                """;
        int result = jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail(),
                student.getAge(),
                id
        );

        if(result <= 0){
            System.out.println("[StudentRepository] updateStudent failed");
            return "something went wrong";
        }
        System.out.println("[StudentRepository] updateStudent: Row affected: " + result);
        return "Row affected: " + result;
    }

    //D- delete
    public String deleteStudentById(long id){
        String sql = """
                DELETE FROM students 
                WHERE id = ?
                """;
        int result = jdbcTemplate.update(
                sql,
                id
        );
        if(result <= 0)
        {
            System.out.println("[StudentRepository] deleteStudentById failed!");
            return "Something went wrong";
        }
        System.out.println("[StudentRepository] deleteStudentById: row affected: " + result);
        return "Row affected: " + result;
    }
}
