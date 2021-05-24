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
public class DisciplineProcRepository {

	private JdbcTemplate jdbcTemplate;

	public DisciplineProcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper<Discipline> MAPPER = new RowMapper<Discipline>() {

		@Override
		public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
			Discipline discipline = new Discipline();
			discipline.setName(rs.getString("disc_name"));
			discipline.setDuration(rs.getInt("disc_dur"));
			discipline.setSemester(rs.getInt("disc_sem"));
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
		String sql = "select * from discipline_list_by_page(" + pageSize + ", " + offset + ")";
		Page<Discipline> page = new PageImpl<>(jdbcTemplate.query(sql, MAPPER), pageable, total);
		return page;
	}

	public Discipline findById(String id) {
		String sql = "select * from discipline_find_by_id('" + id + "')";
		return jdbcTemplate.queryForObject(sql, MAPPER);
	}

	public void deleteById(String id) {
		String sql = "call delete_discipline('" + id + "')";
		jdbcTemplate.execute(sql);
	}

	public void save(Discipline discipline) {
		String sql = String.format("call insert_discipline('%s', '%d', '%d')", discipline.getName(),
				discipline.getDuration(), discipline.getSemester());
		jdbcTemplate.execute(sql);
	}

}
