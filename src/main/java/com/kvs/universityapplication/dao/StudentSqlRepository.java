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
public class StudentSqlRepository {

	private JdbcTemplate jdbcTemplate;

	public StudentSqlRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final ResultSetExtractor<List<Student>> STUDENT_GROUP_MAPPER = new ResultSetExtractor<List<Student>>() {

		@Override
		public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Student> students = new ArrayList<>();
			Group group = null;
			while (rs.next()) {
				String groupName = rs.getString("group_name");
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
		group.setName(rs.getString("group_name"));
		return group;
	}

	private Student mapStudent(ResultSet rs) throws SQLException {
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setName(rs.getString("name"));
		student.setSurname(rs.getString("surname"));
		SchoolDiploma schoolDiploma = new SchoolDiploma();
		schoolDiploma.setNumber(rs.getInt("school_diploma_num"));
		schoolDiploma.setYear(rs.getInt("year"));
		student.setSchoolDiploma(schoolDiploma);
		return student;
	}

	public List<Student> findStudentsGroup(String groupName) {
		String sql = "select student.id, student.name, student.surname, student.group_name,"
				+ " student.school_diploma_num, school_diploma.\"year\" \r\n" + "from student\r\n"
				+ "left join school_diploma on student.school_diploma_num = school_diploma.number\r\n"
				+ "where student.group_name='" + groupName + "'";
		return jdbcTemplate.query(sql, STUDENT_GROUP_MAPPER);
	}

	public Student findById(int id) {
		String sql = "select student.id, student.name, student.surname, student.group_name,"
				+ " student.school_diploma_num, school_diploma.\"year\" \r\n" + "from student\r\n"
				+ "left join school_diploma on student.school_diploma_num = school_diploma.number\r\n"
				+ "where student.id='" + id + "'";
		return jdbcTemplate.query(sql, STUDENT_GROUP_MAPPER).get(0);
	}

	public void deleteById(int id) {
		Student student = findById(id);
		String sql = "delete from school_diploma where number= " + student.getSchoolDiploma().getNumber();
		jdbcTemplate.execute(sql);
	}

	public void save(Student student) {
		int id = student.getId();
		String name = student.getName();
		String surname = student.getSurname();
		String groupName = student.getGroup().getName();
		long diplomaNum = student.getSchoolDiploma().getNumber();
		int diplomaYear = student.getSchoolDiploma().getYear();
		String sqlForSchoolDiploma = String.format(
				"insert into school_diploma values( '%d' , '%d' ) "
						+ "on conflict(number) do update set year='%d', number='%d'",
				diplomaNum, diplomaYear, diplomaYear, diplomaNum);
		String sqlInsertStudent = String.format("insert into student (name, surname, group_name, school_diploma_num) "
				+ "values ('%s', '%s', '%s', '%d')", name, surname, groupName, diplomaNum);
		String sqlUpdateStudent = String
				.format("update student set name='%s', surname='%s', group_name='%s', school_diploma_num='%d' "
						+ "where id='%d'", name, surname, groupName, diplomaNum, id);
		jdbcTemplate.execute(sqlForSchoolDiploma);
		if (id == 0)
			jdbcTemplate.execute(sqlInsertStudent);
		else
			jdbcTemplate.execute(sqlUpdateStudent);

	}

}
