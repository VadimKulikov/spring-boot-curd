package com.kvs.universityapplication.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kvs.universityapplication.entity.Discipline;

@Repository
@org.springframework.transaction.annotation.Transactional
public class DisciplineSqlRepository {

	private JdbcTemplate jdbcTemplate;

	public DisciplineSqlRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper<Discipline> MAPPER = new RowMapper<Discipline>() {

		@Override
		public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
			Discipline discipline = new Discipline();
			discipline.setName(rs.getString("name"));
			discipline.setDuration(rs.getInt("duration"));
			discipline.setSemester(rs.getInt("semester"));
			return discipline;
		}
	};

	public List<Discipline> findAll() {
		String sql = "select * from discipline";
		return jdbcTemplate.query(sql, MAPPER);
	}

	public Page<Discipline> findAll(Pageable pageable) {
		int total = jdbcTemplate.queryForObject("select count(1) from discipline", new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("count");
			}

		});
		long offset = pageable.getOffset();
		int pageSize = pageable.getPageSize();
		String sql = "select * " + "from discipline " + "order by name limit " + pageSize + " offset " + offset;
		Page<Discipline> page = new PageImpl<>(jdbcTemplate.query(sql, MAPPER), pageable, total);
		return page;
	}

	public Discipline findById(String id) {
		String sql = "select * from discipline where name ='" + id + "'";
		return jdbcTemplate.queryForObject(sql, MAPPER);
	}

	public void deleteById(String id) {
		String sql = "delete from discipline where name ='" + id + "'";
		jdbcTemplate.execute(sql);
	}

	public void save(Discipline discipline) {
		String sql = String.format(
				"insert into discipline values ('%s', '%d', '%d') on conflict (name) do update set "
						+ "duration='%d', semester='%d'",
				discipline.getName(), discipline.getDuration(), discipline.getSemester(), discipline.getDuration(),
				discipline.getSemester());
		jdbcTemplate.execute(sql);
	}

}
