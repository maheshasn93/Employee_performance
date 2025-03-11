package com.example.employeeperformance;

import com.example.employeeperformance.Entity.Employee;
import com.example.employeeperformance.Entity.RatingCategory;
import com.example.employeeperformance.Repository.EmployeeRepository;
import com.example.employeeperformance.Repository.RatingCategoryRepository;
import com.example.employeeperformance.Service.EmployeePerformanceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeePerformanceServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RatingCategoryRepository ratingCategoryRepository;

    @InjectMocks
    private EmployeePerformanceService employeePerformanceService;

    private List<Employee> employeeList;
    private List<RatingCategory> ratingCategories;

    @BeforeEach
    void setUp() {
        employeeList = Arrays.asList(
                new Employee(5001L, "Harry 1", "A"),
                new Employee(5002L, "Harry 3", "B"),
                new Employee(5003L, "Harry 4", "C"),
                new Employee(5004L, "Harry 2", "D"),
                new Employee(5005L, "Harry 5", "E"),
                new Employee(5006L, "Harry 7", "A"),
                new Employee(5007L, "Harry 6", "C"),
                new Employee(5008L, "Harry 8", "D"),
                new Employee(5009L, "Harry 9", "B"),
                new Employee(5010L, "Harry 10", "E"),
                new Employee(5011L, "Harry 13", "A"),
                new Employee(5012L, "Harry 12", "C"),
                new Employee(5013L, "Harry 14", "D"),
                new Employee(5014L, "Harry 15", "C"),
                new Employee(5015L, "Harry 11", "E")
        );

        ratingCategories = Arrays.asList(
                new RatingCategory("A", 10.0f),
                new RatingCategory("B", 20.0f),
                new RatingCategory("C", 40.0f),
                new RatingCategory("D", 20.0f),
                new RatingCategory("E", 10.0f)
        );

        when(employeeRepository.findAll()).thenReturn(employeeList);
        when(ratingCategoryRepository.findAll()).thenReturn(ratingCategories);
    }

    @Test
    void testCalculateActualPercentage() {
        Map<String, Double> actualPercentage = employeePerformanceService.calculateActualPercentage();

        assertNotNull(actualPercentage);
        assertEquals(20.0, actualPercentage.get("A"), 0.01);
        assertEquals(13.33, actualPercentage.get("B"), 0.01);
        assertEquals(33.33, actualPercentage.get("C"), 0.01);
        assertEquals(20.0, actualPercentage.get("D"), 0.01);
        assertEquals(13.33, actualPercentage.get("E"), 0.01);
    }

    @Test
    void testCalculateDeviation() {
        Map<String, Double> deviation = employeePerformanceService.calculateDeviation();

        assertNotNull(deviation);
        assertEquals(10.0, deviation.get("A"), 0.01);
        assertEquals(-6.67, deviation.get("B"), 0.01);
        assertEquals(-6.67, deviation.get("C"), 0.01);
        assertEquals(0.0, deviation.get("D"), 0.01);
        assertEquals(3.33, deviation.get("E"), 0.01);
    }

    @Test
    void testSuggestRatingAdjustments() {
        Map<String, String> suggestedChanges = employeePerformanceService.suggestRatingAdjustments();

        System.out.println("Suggested Changes: " + suggestedChanges);

        assertNotNull(suggestedChanges);
        assertFalse(suggestedChanges.isEmpty(), "Expected non-empty suggested rating adjustments");
    }
}