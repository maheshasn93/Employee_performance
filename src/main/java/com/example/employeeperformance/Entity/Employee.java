package com.example.employeeperformance.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT DEFAULT 5001")
    private Long id;
    private String name;
    private String rating;
    
    public Employee(Long id, String name, String rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }
    

	public Employee() {
		super();
	}


	// Getters and Setters
    public Long getId() 
    { 
    	return id; 
    }
    
    public void setId(Long id) 
    { 
    	this.id = id; 
    }
    
    public String getName() 
    { 
    	return name; 
    }
    
    public void setName(String name) 
    { 
    	this.name = name; 
    }
    
    public String getRating() 
    { 
    	return rating; 
    }
    
    public void setRating(String rating) 
    { 
    	this.rating = rating; 
    }

}
