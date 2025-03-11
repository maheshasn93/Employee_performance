package com.example.employeeperformance.Controller;

import com.example.employeeperformance.Service.EmployeePerformanceService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/performance")
public class EmployeePerformanceController {
	private final EmployeePerformanceService service;

    public EmployeePerformanceController(EmployeePerformanceService service) {
        this.service = service;
    }

    @GetMapping("/actual-percentage")
    public Map<String, Double> getActualPercentage() {
        return service.calculateActualPercentage();
    }

    @GetMapping("/deviation")
    public Map<String, Double> getDeviation() {
        return service.calculateDeviation();
    }

    @GetMapping("/suggested-changes")
    public Map<String, String> getSuggestedChanges() {
        return service.suggestRatingAdjustments();
    }
    
    @GetMapping("/total-employees")
    public Map<String, Integer> getTotalEmployees() {
        int totalEmployees =service.getTotalEmployeeCount();
        return Collections.singletonMap("totalEmployees", totalEmployees);
    }

}
