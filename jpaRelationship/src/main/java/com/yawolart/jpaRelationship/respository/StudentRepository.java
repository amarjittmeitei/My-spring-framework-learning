package com.yawolart.jpaRelationship.respository;

import com.yawolart.jpaRelationship.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void save(Student student){
        entityManager.persist(student);
    }

    public Student findById(Long id){
        return entityManager.find(Student.class, id);
    }

    public void removeStudent(Student student){
        entityManager.remove(student);
    }
}
