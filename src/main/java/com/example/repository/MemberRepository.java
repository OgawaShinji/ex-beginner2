package com.example.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Member;

@Repository
public class MemberRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	private static final RowMapper<Member> MEMBER_ROW_MAPPER = (rs, i) -> {
		Member member=new Member();
		member.setName(rs.getString("name"));
		return member;
	};
	
	public List<Member> findLike(String name){
		String sql="SELECT name FROM members WHERE name LIKE :name ORDER BY id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("name", "%"+name+"%");
		List<Member> membersList=template.query(sql, param, MEMBER_ROW_MAPPER);
		return membersList;
		
	
	}
	
	

}
