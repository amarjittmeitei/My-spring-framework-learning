package com.yawolart.hibernateDemo.repository;

import com.yawolart.hibernateDemo.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    //for application.properties
    /*
        spring.datasource.url = jdbc:mysql://localhost:3306/student_jdbc_demo
        spring.datasource.username =
        spring.datasource.password =

        spring.jpa.hibernate.ddl-auto = update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
     */



    //this is a field injection with a smart proxy
    //  -> SharedEntityManagerProxy
    //this annotation can also use for constructor and setter injection
    @PersistenceContext
    private EntityManager entityManager;


    //create
    public void save(Student student){
        entityManager.persist(student);
        //this persist() just put the student entity to
        //persistence context. It does not insert the entity to database
        //so to insert this student entity to database, this save method
        //needs to be called in a transactional method. Otherwise, the entity will
        //never insert to database as entityManager.flush() never called.
        //Or call the flush() method explicitly
        //like below

        //entityManager.flush();

        //But, we will call this save method inside a transactional method
        //so, we don't need to explicitly called the flush() method.
    }

    //read
    public Student findById(Long id){
        return entityManager.find(Student.class,id);
    }

    //update -> there is not update method see note v29

    //delete
    public void removeStudent(Student student){
        entityManager.remove(student);
    }
}
