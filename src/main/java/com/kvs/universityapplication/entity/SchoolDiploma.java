package com.kvs.universityapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="school_diploma")
public class SchoolDiploma {
	
	@Id
	@Column(name="number")
	private long number;
	
	@Column(name="year")
	private int year;
	
	@OneToOne(mappedBy="schoolDiploma")
	private Student student;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
