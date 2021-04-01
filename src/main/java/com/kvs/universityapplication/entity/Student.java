package com.kvs.universityapplication.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="patrynomic")
	private String patrynomic;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="group_name")
	private Group group;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="school_diploma_num")
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

	public String getPatrynomic() {
		return patrynomic;
	}

	public void setPatrynomic(String patrynomic) {
		this.patrynomic = patrynomic;
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
