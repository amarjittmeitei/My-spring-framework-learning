package com.yawolart.jpaRelationship.respository;

import com.yawolart.jpaRelationship.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void save(Department department){
        entityManager.persist(department);
    }

    public Department findById(Long id){
        return entityManager.find(Department.class,id);
    }

    public void removeDepartment(Department department){
        entityManager.remove(department);
    }
}
