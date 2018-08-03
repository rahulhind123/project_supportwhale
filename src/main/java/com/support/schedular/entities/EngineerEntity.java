package com.support.schedular.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EngineerEntity {
	

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="engineer_name")
	private String name;
	
	private int shiftNumber;


	public int getShiftNumber() {
		return shiftNumber;
	}

	public void setShiftNumber(int shiftNumber) {
		this.shiftNumber = shiftNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public boolean equals(Object other){
	    if (!(other instanceof EngineerEntity))
	        return false;
	    EngineerEntity engineer = (EngineerEntity) other;
	    return (this.id==engineer.id) && 
	           (this.getName().equals(engineer.getName()));
	}

	@Override
	public String toString() {
		return "EngineerEntity [id=" + id + ", name=" + name + ", shiftNumber="+ shiftNumber + "]";
	}
	

	
}
