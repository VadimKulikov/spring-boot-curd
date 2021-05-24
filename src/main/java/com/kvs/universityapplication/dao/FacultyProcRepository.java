package com.kvs.universityapplication.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kvs.universityapplication.entity.Department;
import com.kvs.universityapplication.entity.Faculty;

@Repository
@Transactional
public class FacultyProcRepository {

	private final JdbcTemplate jdbcTemplate;

	public FacultyProcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final ResultSetExtractor<List<Faculty>> MAPPER = new ResultSetExtractor<>() {

		@Override
		public List<Faculty> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Faculty> faculties = new ArrayList<>();
			Faculty faculty = null;
			while (rs.next()) {
				int id = rs.getInt("faculty_id");
				if (faculty == null) { // initial object
					faculty = mapFaculty(rs);
				} else if (faculty.getId() != id) { // break
					faculties.add(faculty);
					faculty = mapFaculty(rs);
				}
				faculty.addDepartment(mapDepartment(rs));
			}
			if (faculty != null) { // last object
				faculties.add(faculty);
			}
			return faculties;
		}

	};

	private Faculty mapFaculty(ResultSet rs) throws SQLException {
		Faculty faculty = new Faculty();
		faculty.setId(rs.getInt("faculty_id"));
		faculty.setName(rs.getString("faculty_name"));
		return faculty;
	}

	private Department mapDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setAbbr(rs.getString("department_abbr"));
		return department;
	}

	private final RowMapper<Faculty> MAP_FACULTY = new RowMapper<>() {

		@Override
		public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
			Faculty faculty = new Faculty();
			faculty.setId(rs.getInt("faculty_id"));
			faculty.setName(rs.getString("faculty_name"));
			return faculty;
		}
	};

	public List<Faculty> findAll() {
		String sql = "select * from faculty_list()";
		return jdbcTemplate.query(sql, MAPPER);
	}

	public void save(Faculty faculty) {
		String sql;
		if (faculty.getId() == 0) {
			sql = String.format("call insert_faculty('%s')", faculty.getName());

		} else {
			sql = String.format("call update_faculty('%s','%d')", faculty.getName(), faculty.getId());
		}

		jdbcTemplate.execute(sql);
	}

	public void update(Faculty faculty) {
		String sql = String.format("call update_faculty('%s','%d')", faculty.getName(), faculty.getId());
		jdbcTemplate.execute(sql);
	}

	public Faculty findById(int id) {
		String sql = "select * from faculty_by_id(" + id + ")";
		return jdbcTemplate.queryForObject(sql, MAP_FACULTY);
	}

	public void deleteById(int id) {
		String sql = "call delete_faculty(" + id + ")";
		jdbcTemplate.execute(sql);
	}

}
