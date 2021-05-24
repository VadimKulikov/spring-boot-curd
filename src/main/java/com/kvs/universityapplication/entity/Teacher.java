package com.kvs.universityapplication.entity;

import java.util.HashSet;
import java.util.Set;

public class Teacher {

	private int id;

	private String name;

	private String surname;

	private String patronymic;

	private Department department;

	private String academicTitle;

	private Set<Discipline> disciplines = new HashSet<>();

	public Teacher() {

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department dpeartment) {
		this.department = dpeartment;
	}

	public String getAcademicTitle() {
		return academicTitle;
	}

	public void setAcademicTitle(String academicTitle) {
		this.academicTitle = academicTitle;
	}

	public Set<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(Set<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	public void addDiscipline(Discipline discipline) {
		this.disciplines.add(discipline);
	}

}
