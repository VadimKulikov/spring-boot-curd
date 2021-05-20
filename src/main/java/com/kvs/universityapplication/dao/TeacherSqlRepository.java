package com.kvs.universityapplication.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kvs.universityapplication.entity.Department;
import com.kvs.universityapplication.entity.Discipline;
import com.kvs.universityapplication.entity.Teacher;

@org.springframework.transaction.annotation.Transactional
@Repository
public class TeacherSqlRepository {
	private JdbcTemplate jdbcTemplate;

	public TeacherSqlRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private ResultSetExtractor<List<Teacher>> MAPPER = new ResultSetExtractor<>() {

		@Override
		public List<Teacher> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Teacher> teachers = new ArrayList<>();
			Teacher teacher = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				if (teacher == null) {
					teacher = mapTeacher(rs);
				} else if (id != teacher.getId()) {
					teachers.add(teacher);
					teacher = mapTeacher(rs);
				}
				if (rs.getString("discipline_name") != null) {
					Discipline discipline = mapDiscipline(rs);
					teacher.addDiscipline(discipline);
				}

			}
			if (teacher != null) {
				teachers.add(teacher);
			}

			return teachers;
		}
	};

	private Teacher mapTeacher(ResultSet rs) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setId(rs.getInt("id"));
		teacher.setName(rs.getString("name"));
		teacher.setSurname(rs.getString("surname"));
		teacher.setPatronymic(rs.getString("patronymic"));
		teacher.setAcademicTitle(rs.getString("academic_title"));
		Department department = new Department();
		department.setAbbr(rs.getString("department_name"));
		teacher.setDepartment(department);
		return teacher;
	}

	private Discipline mapDiscipline(ResultSet rs) throws SQLException {
		Discipline discipline = new Discipline();
		discipline.setName(rs.getString("discipline_name"));
		return discipline;
	}

	public Page<Teacher> findAll(Pageable pageable) {
		int total = jdbcTemplate.queryForObject("select count(1) from teacher", new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}

		});
		long offset = pageable.getOffset();
		int pageSize = pageable.getPageSize();
		String sql = "select teacher.id, teacher.name, teacher.surname, teacher.patronymic, teacher.department_name, \r\n"
				+ "teacher.academic_title , teacher_discipline.discipline_name \r\n" + "from teacher\r\n"
				+ "left join teacher_discipline on teacher_discipline.teacher_id = teacher.id\r\n"
				+ "order by teacher.id limit " + pageSize + " offset " + offset;
		Page<Teacher> page = new PageImpl<>(jdbcTemplate.query(sql, MAPPER), pageable, total);
		return page;
	}

	public Teacher findById(int id) {
		String sql = "select teacher.id, teacher.name, teacher.surname, teacher.patronymic, teacher.department_name, \r\n"
				+ "teacher.academic_title , teacher_discipline.discipline_name \r\n" + "from teacher\r\n"
				+ "left join teacher_discipline on teacher_discipline.teacher_id = teacher.id\r\n"
				+ "where teacher.id='" + id + "'";

		return jdbcTemplate.query(sql, MAPPER).get(0);
	}

	public void save(Teacher teacher) {
		int id = teacher.getId();
		String name = teacher.getName();
		String surname = teacher.getSurname();
		String patronymic = teacher.getPatronymic();
		String academicTitle = teacher.getAcademicTitle();
		String deptName = teacher.getDepartment().getAbbr();
		String sqlInsertTeacher = String
				.format("insert into teacher (name,surname,patronymic,department_name, academic_title) values"
						+ " ('%s', '%s', '%s', '%s' , '%s')", name, surname, patronymic, deptName, academicTitle);
		String sqlUpdateTeacher = String.format(
				"update teacher set name='%s', surname='%s', patronymic='%s',"
						+ "department_name='%s', academic_title='%s' where id=" + id,
				name, surname, patronymic, deptName, academicTitle);

		if (id != 0)
			jdbcTemplate.execute(sqlUpdateTeacher);
		else
			jdbcTemplate.execute(sqlInsertTeacher);

		String insertSql = "insert into teacher_discipline values ('%d', '%s')";

		int newId = FindJustInsertedTeacherId(teacher);

		if (id != 0) {
			deleteAllTeacherRecords(newId);
		}
		for (Discipline discipline : teacher.getDisciplines()) {
			jdbcTemplate.execute(String.format(insertSql, newId, discipline.getName()));

		}

	}

	public int FindJustInsertedTeacherId(Teacher teacher) {
		String sql = String.format("select teacher.id from teacher where name='%s'", teacher.getName());
		return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("id");
			}

		});
	}

	public void deleteById(int id) {
		String sql = "delete from teacher where id='" + id + "'";
		jdbcTemplate.execute(sql);
	}

	private void deleteAllTeacherRecords(int id) {
		String sql = "delete from teacher_discipline where teacher_id=" + id;
		jdbcTemplate.execute(sql);
	}

}
