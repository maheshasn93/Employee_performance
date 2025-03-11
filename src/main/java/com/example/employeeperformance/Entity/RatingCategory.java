package com.example.employeeperformance.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating_category")
public class RatingCategory {
	@Id
    private String category;
    private float standardPercentage;
    
    
    
    public RatingCategory() {
		super();
	}

	public RatingCategory(String category, float standardPercentage) {
		this.category = category;
		this.standardPercentage = standardPercentage;
	}

	// Getters and Setters
    public String getCategory() 
    { 
    	return category; 
    }
    
    public void setCategory(String category) 
    { 
    	this.category = category; 
    }
    
    public float getStandardPercentage() 
    {  
    	return standardPercentage; 
    }
    
    public void setStandardPercentage(float standardPercentage) 
    {  
    	this.standardPercentage = standardPercentage;  
    }
}
