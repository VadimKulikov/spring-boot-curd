package com.kvs.universityapplication.entity;


import java.util.HashSet;
import java.util.Set;


public class Department {
	
	// сделать аббревиатуру

	private String abbr;
	
	private String name;
	
	private Faculty faculty;

	private Set<Group> groups = new HashSet<>(); 

	private Set<Teacher> teachers;
	
	public Department() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@Override
	public String toString() {
		return abbr;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public void addGroup(Group group) {
		groups.add(group);
		group.setDepartment(this);
	}
	
	
	
}
