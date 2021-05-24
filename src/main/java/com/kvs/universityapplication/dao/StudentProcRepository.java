package com.kvs.universityapplication.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.kvs.universityapplication.entity.Group;
import com.kvs.universityapplication.entity.SchoolDiploma;
import com.kvs.universityapplication.entity.Student;

@Repository
@org.springframework.transaction.annotation.Transactional
public class StudentProcRepository {

	private JdbcTemplate jdbcTemplate;

	public StudentProcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final ResultSetExtractor<List<Student>> STUDENT_GROUP_MAPPER = new ResultSetExtractor<List<Student>>() {

		@Override
		public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Student> students = new ArrayList<>();
			Group group = null;
			while (rs.next()) {
				String groupName = rs.getString("student_groupname");
				if (group == null) {
					group = mapGroup(rs);
				} else if (!group.getName().equals(groupName)) {
					group = mapGroup(rs);
				}
				Student student = mapStudent(rs);
				group.addStudent(student);
				students.add(student);
			}
			return students;
		}
	};

	private Group mapGroup(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setName(rs.getString("student_groupname"));
		return group;
	}

	private Student mapStudent(ResultSet rs) throws SQLException {
		Student student = new Student();
		student.setId(rs.getInt("student_id"));
		student.setName(rs.getString("student_name"));
		student.setSurname(rs.getString("student_surname"));
		SchoolDiploma schoolDiploma = new SchoolDiploma();
		schoolDiploma.setNumber(rs.getInt("student_diploma_num"));
		schoolDiploma.setYear(rs.getInt("school_diploma_year"));
		student.setSchoolDiploma(schoolDiploma);
		return student;
	}

	public List<Student> findStudentsGroup(String groupName) {
		String sql = "select * from students_group('" + groupName + "')";
		return jdbcTemplate.query(sql, STUDENT_GROUP_MAPPER);
	}

	public Student findById(int id) {
		String sql = "select * from student_find_by_id('" + id + "')";
		return jdbcTemplate.query(sql, STUDENT_GROUP_MAPPER).get(0);
	}

	public void deleteById(int id) {
		Student student = findById(id);
		String sql = "call delete_student('" + student.getSchoolDiploma().getNumber() + "')";
		jdbcTemplate.execute(sql);
	}

	public void save(Student student) {
		int id = student.getId();
		String name = student.getName();
		String surname = student.getSurname();
		String groupName = student.getGroup().getName();
		long diplomaNum = student.getSchoolDiploma().getNumber();
		int diplomaYear = student.getSchoolDiploma().getYear();
		String sqlForSchoolDiploma = String.format("call insert_diploma('%d', '%d')", diplomaNum, diplomaYear);
		String sqlInsertStudent = String.format("call insert_student('%s', '%s', '%s', '%d')", name, surname, groupName,
				diplomaNum);
		String sqlUpdateStudent = String.format("call update_student('%s', '%s', '%s', '%s', '%d')", name, surname,
				groupName, diplomaNum, id);
		jdbcTemplate.execute(sqlForSchoolDiploma);
		if (id == 0)
			jdbcTemplate.execute(sqlInsertStudent);
		else
			jdbcTemplate.execute(sqlUpdateStudent);

	}

}
