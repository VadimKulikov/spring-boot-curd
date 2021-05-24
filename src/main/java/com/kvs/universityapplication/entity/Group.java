package com.kvs.universityapplication.entity;

import java.util.HashSet;
import java.util.Set;

public class Group {

	private String name;

	private int year;

	private Department department;

	private Set<Student> students = new HashSet<>();

	public Group() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
		student.setGroup(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
