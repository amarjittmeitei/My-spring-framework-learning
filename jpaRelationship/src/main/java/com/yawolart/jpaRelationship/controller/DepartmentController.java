package com.yawolart.jpaRelationship.controller;

import com.yawolart.jpaRelationship.model.Department;
import com.yawolart.jpaRelationship.service.DepartmentService;
import com.yawolart.jpaRelationship.service.StudentService;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> departmentPostController(
            @RequestBody
            Department department
    ){
        departmentService.createDepartment(department);
        return ResponseEntity.ok("DONE");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> departmentGetController(
            @PathVariable
            Long id
    ){
        Department department = departmentService.findDepartment(id);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> departmentDeleteController(
            @PathVariable
            Long id
    ){
        departmentService.removeDepartment(id);
        return ResponseEntity.ok("DONE");
    }
}
