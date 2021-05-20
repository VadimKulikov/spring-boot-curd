package com.kvs.universityapplication.entity;

import java.util.HashSet;
import java.util.Set;



public class Faculty {

	private int id;

	private String name;

	private Set<Department> departments = new HashSet<>();

	public Faculty() {
		
	}
	
	public Faculty(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}


	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
	public void addDepartment(Department department) {
		department.setFaculty(this);
		departments.add(department);
	}

}
