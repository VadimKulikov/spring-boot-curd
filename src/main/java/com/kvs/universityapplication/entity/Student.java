package com.kvs.universityapplication.entity;

public class Student {

	private int id;

	private String name;

	private String surname;

	private Group group;

	private SchoolDiploma schoolDiploma;

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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public SchoolDiploma getSchoolDiploma() {
		return schoolDiploma;
	}

	public void setSchoolDiploma(SchoolDiploma schoolDiploma) {
		this.schoolDiploma = schoolDiploma;
	}

}
