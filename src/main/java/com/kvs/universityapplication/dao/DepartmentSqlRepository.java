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
import com.kvs.universityapplication.entity.Faculty;

@Repository
@org.springframework.transaction.annotation.Transactional
public class DepartmentSqlRepository {

	private JdbcTemplate jdbcTemplate;

	private final ResultSetExtractor<List<Department>> MAPPER = new ResultSetExtractor<>() {

		@Override
		public List<Department> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Department> departments = new ArrayList<>();
			Faculty faculty = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				if (faculty == null) {
					faculty = mapFaculty(rs);
				} else if (faculty.getId() != id) { // break
					faculty = mapFaculty(rs);
				}
				Department department = mapDepartment(rs);
				faculty.addDepartment(department);
				departments.add(department);
			}
			return departments;
		}

	};

	private Faculty mapFaculty(ResultSet rs) throws SQLException {
		Faculty faculty = new Faculty();
		faculty.setId(rs.getInt("id"));
		faculty.setName(rs.getString("faculty_name"));
		return faculty;
	}

	private Department mapDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setAbbr(rs.getString("abbr"));
		department.setName(rs.getString("name"));
		return department;
	}

	public DepartmentSqlRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Page<Department> findAll(Pageable pageable) {
		int total = jdbcTemplate.queryForObject("select count(1) from department", new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}

		});
		long offset = pageable.getOffset();
		int pageSize = pageable.getPageSize();
		String sql = "select department.abbr, department.name, faculty.id, faculty.\"name\" as faculty_name "
				+ "from department " + "left join faculty on department.faculty_id = faculty.id "
				+ "order by faculty.\"name\" limit " + pageSize + " offset " + offset;
		Page<Department> page = new PageImpl<>(jdbcTemplate.query(sql, MAPPER), pageable, total);
		return page;
	}

	public List<Department> findAll() {
		String sql = "select department.abbr, department.name,faculty.id, faculty.\"name\" as faculty_name "
				+ "from department " + "left join faculty on department.faculty_id = faculty.id "
				+ "order by faculty.\"name\" ";
		return jdbcTemplate.query(sql, MAPPER);
	}

	public void deleteById(String id) {
		String sql = "delete from department where abbr=" + id;
		jdbcTemplate.execute(sql);
	}

	public void save(Department department) {
		String abbr = department.getAbbr();
		int facultyId = department.getFaculty().getId();
		String name = department.getName();
		String sql = String.format(
				"insert into department (abbr, faculty_id, name) values ('%s', '%d', '%s') "
						+ "on conflict (abbr) do update set faculty_id='%d', name='%s'",
				abbr, facultyId, name, facultyId, name);
		jdbcTemplate.execute(sql);
	}

	public Department findById(String id) {
		String sql = "select department.abbr, department.name,faculty.id, faculty.\"name\" as faculty_name "
				+ "from department " + "left join faculty on department.faculty_id = faculty.id where department.abbr='"
				+ id + "'";
		return jdbcTemplate.query(sql, MAPPER).get(0);
	}

}
