package com.example.employeeperformance.Service;

import com.example.employeeperformance.Entity.Employee;
import com.example.employeeperformance.Entity.RatingCategory;
import com.example.employeeperformance.Repository.EmployeeRepository;
import com.example.employeeperformance.Repository.RatingCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeePerformanceService {
	private final EmployeeRepository employeeRepository;
    private final RatingCategoryRepository ratingCategoryRepository;

    public EmployeePerformanceService(EmployeeRepository employeeRepository, RatingCategoryRepository ratingCategoryRepository) {
        this.employeeRepository = employeeRepository;
        this.ratingCategoryRepository = ratingCategoryRepository;
    }
    
    public int getTotalEmployeeCount() {
        return (int) employeeRepository.count();
    }

    public Map<String, Double> calculateActualPercentage() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, Integer> ratingCount = new HashMap<>();
        for (Employee e : employees) {
            ratingCount.put(e.getRating(), ratingCount.getOrDefault(e.getRating(), 0) + 1);
        }

        int totalEmployees = employees.size();
        Map<String, Double> actualPercentage = new HashMap<>();
        for (Map.Entry<String, Integer> entry : ratingCount.entrySet()) {
            actualPercentage.put(entry.getKey(), (entry.getValue() * 100.0) / totalEmployees);
        }
        return actualPercentage;
    }

    public Map<String, Double> calculateDeviation() {
        Map<String, Double> actualPercentage = calculateActualPercentage();
        List<RatingCategory> categories = ratingCategoryRepository.findAll();
        Map<String, Double> deviation = new HashMap<>();

        for (RatingCategory category : categories) {
            double actual = actualPercentage.getOrDefault(category.getCategory(), 0.0);
            deviation.put(category.getCategory(), actual - category.getStandardPercentage());
        }
        return deviation;
    }

    public Map<String, String> suggestRatingAdjustments() {
        Map<String, Double> deviation = calculateDeviation();
        List<Employee> employees = employeeRepository.findAll();
        Map<String, String> suggestedChanges = new LinkedHashMap<>();

        List<Employee> excessEmployees = new ArrayList<>();
        List<String> underrepresentedCategories = new ArrayList<>();

        // Find overrepresented categories
        for (Map.Entry<String, Double> entry : deviation.entrySet()) {
            if (entry.getValue() > 0) { // Overrepresented
                for (Employee employee : employees) {
                    if (employee.getRating().equals(entry.getKey())) {
                        excessEmployees.add(employee);
                        if (excessEmployees.size() >= Math.abs(entry.getValue())) {
                            break;
                        }
                    }
                }
            } else if (entry.getValue() < 0) { // Underrepresented
                underrepresentedCategories.add(entry.getKey());
            }
        }

        // Suggest new ratings
        for (int i = 0; i < excessEmployees.size() && i < underrepresentedCategories.size(); i++) {
            Employee emp = excessEmployees.get(i);
            suggestedChanges.put(emp.getName() + " (ID: " + emp.getId() + ", Rating: " + emp.getRating() + ")", 
                                 "Suggested Rating: " + underrepresentedCategories.get(i));
        }

        return suggestedChanges;
    }

}
