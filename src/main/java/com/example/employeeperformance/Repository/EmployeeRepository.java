package com.example.employeeperformance.Repository;

import com.example.employeeperformance.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
