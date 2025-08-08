package com.example.demo.securitysample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AppUserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public AppUser findUserAccount(String userName) {

		String sql = AppUserMapper.BASE_SQL + " where u.username = ? and u.enabled !=0 ";
		System.out.println(sql);
		Object[] params = new Object[] { userName };
		AppUserMapper mapper = new AppUserMapper();
		try {

			AppUser userInfo = jdbcTemplate.queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
			return null;
		}
	}

}
