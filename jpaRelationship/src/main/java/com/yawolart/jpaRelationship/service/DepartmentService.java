package com.yawolart.jpaRelationship.service;

import com.yawolart.jpaRelationship.model.Department;
import com.yawolart.jpaRelationship.respository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void createDepartment(Department department){
        departmentRepository.save(department);
    }

    public Department findDepartment(Long id){
        return departmentRepository.findById(id);
    }

    @Transactional
    public void removeDepartment(Long id){
        Department department = departmentRepository.findById(id);
        departmentRepository.removeDepartment(department);
    }
}
