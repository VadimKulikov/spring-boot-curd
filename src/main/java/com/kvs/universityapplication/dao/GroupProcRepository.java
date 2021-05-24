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
import com.kvs.universityapplication.entity.Group;

@Repository
@org.springframework.transaction.annotation.Transactional
public class GroupProcRepository {

	private JdbcTemplate jdbcTemplate;

	public GroupProcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final ResultSetExtractor<List<Group>> MAPPER = new ResultSetExtractor<>() {

		@Override
		public List<Group> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Group> groups = new ArrayList<>();
			Department department = null;
			while (rs.next()) {
				String departmentName = rs.getString("dept_fullname");
				if (department == null) { // initial object
					department = mapDepartment(rs);
				} else if (department.getName() != departmentName) { // break
					department = mapDepartment(rs);
				}
				Group group = mapGroup(rs);
				department.addGroup(group);
				groups.add(group);
			}
			return groups;
		}

	};

	private Department mapDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setAbbr(rs.getString("dept_name"));
		department.setName(rs.getString("dept_fullname"));
		return department;
	}

	private Group mapGroup(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setName(rs.getString("group_name"));
		group.setYear(rs.getInt("group_year"));
		return group;
	}

//	public List<Group> findAll() {
//		String sql = "select groups.\"name\" , groups.\"year\" , groups.department_name, department.name as depname\r\n"
//				+ "from \"groups\"\r\n" + "left join department on department_name = department.abbr\r\n"
//				+ "order by department_name";
//		return jdbcTemplate.query(sql, MAPPER);
//	}

	public Page<Group> findAll(Pageable pageable) {
		int total = jdbcTemplate.queryForObject("select count(1) from groups", new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}

		});
		long offset = pageable.getOffset();
		int pageSize = pageable.getPageSize();
		String sql = "select * from group_list_by_page(" + pageSize + ", " + offset + ")";
		Page<Group> page = new PageImpl<>(jdbcTemplate.query(sql, MAPPER), pageable, total);
		return page;
	}

	public Group findById(String Id) {
		String sql = "select * from group_find_by_id('" + Id + "')";
		return jdbcTemplate.query(sql, MAPPER).get(0);
	}

	public void save(Group group) {
		String name = group.getName();
		int year = group.getYear();
		String departmentName = group.getDepartment().getAbbr();
		String sql = String.format("call insert_group('%s', '%d', '%s')", name, year, departmentName);
		jdbcTemplate.execute(sql);
	}

	public void deleteById(String Id) {
		String sql = "call delete_group('" + Id + "')";
		jdbcTemplate.execute(sql);
	}

}
