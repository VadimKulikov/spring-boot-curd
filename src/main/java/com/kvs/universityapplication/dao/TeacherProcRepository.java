package com.kvs.universityapplication.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
public class TeacherProcRepository {
	private JdbcTemplate jdbcTemplate;

	public TeacherProcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private ResultSetExtractor<List<Teacher>> MAPPER = new ResultSetExtractor<>() {

		@Override
		public List<Teacher> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Teacher> teachers = new ArrayList<>();
			Teacher teacher = null;
			while (rs.next()) {
				int id = rs.getInt("teacher_id");
				if (teacher == null) {
					teacher = mapTeacher(rs);
				} else if (id != teacher.getId()) {
					teachers.add(teacher);
					teacher = mapTeacher(rs);
				}
				if (rs.getString("teacher_disc_name") != null) {
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
		teacher.setId(rs.getInt("teacher_id"));
		teacher.setName(rs.getString("teacher_name"));
		teacher.setSurname(rs.getString("teacher_surname"));
		teacher.setPatronymic(rs.getString("teacher_patronymic"));
		teacher.setAcademicTitle(rs.getString("teacher_title"));
		Department department = new Department();
		department.setAbbr(rs.getString("teacher_department"));
		teacher.setDepartment(department);
		return teacher;
	}

	private Discipline mapDiscipline(ResultSet rs) throws SQLException {
		Discipline discipline = new Discipline();
		discipline.setName(rs.getString("teacher_disc_name"));
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
		String sql = "select * from teacher_list_by_page(" + pageSize + ", " + offset + ")";
		Page<Teacher> page = new PageImpl<>(jdbcTemplate.query(sql, MAPPER), pageable, total);
		return page;
	}

	public Teacher findById(int id) {
		String sql = "select * from teacher_find_by_id('" + id + "')";

		return jdbcTemplate.query(sql, MAPPER).get(0);
	}

	public void save(Teacher teacher) {
		int id = teacher.getId();
		String name = teacher.getName();
		String surname = teacher.getSurname();
		String patronymic = teacher.getPatronymic();
		String academicTitle = teacher.getAcademicTitle();
		String deptName = teacher.getDepartment().getAbbr();
		String sqlInsertTeacher = String.format("call insert_teacher('%s', '%s', '%s', '%s' , '%s')", name, surname,
				patronymic, deptName, academicTitle);
		String sqlUpdateTeacher = String.format("call update_teacher('%s', '%s', '%s', '%s' , '%s', '%d')", name,
				surname, patronymic, deptName, academicTitle, id);

		if (id != 0)
			jdbcTemplate.execute(sqlUpdateTeacher);
		else
			jdbcTemplate.execute(sqlInsertTeacher);

		String insertSql = "insert into teacher_discipline values ('%d', '%s')";

		int newId = FindJustInsertedTeacherId(teacher);

		if (id != 0) {
			deleteAllTeacherRecords(id);
		}
		for (Discipline discipline : teacher.getDisciplines()) {
			jdbcTemplate.execute(String.format(insertSql, newId, discipline.getName()));

		}

	}

	public int FindJustInsertedTeacherId(Teacher teacher) {
		String sql = String.format("select teacher.id from teacher where name='%s' and surname='%s'", teacher.getName(),
				teacher.getSurname());
		return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("id");
			}

		});
	}

	public void deleteById(int id) {
		String sql = "call delete_teacher(" + id + ")";
		jdbcTemplate.execute(sql);
	}

	private void deleteAllTeacherRecords(int id) {
		String sql = "call delete_teacher_records(" + id + ")";
		jdbcTemplate.execute(sql);
	}

}
