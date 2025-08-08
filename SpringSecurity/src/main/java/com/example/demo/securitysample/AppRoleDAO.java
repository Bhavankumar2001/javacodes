package com.example.demo.securitysample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AppRoleDAO extends JdbcDaoSupport {

   @Autowired
   public AppRoleDAO(DataSource dataSource) {
       this.setDataSource(dataSource);
   }

   public List<String> getRoleNames(Integer userId) {
       String sql = "select  r.role from users u left join `role` r on r.id =u.role_id where u.id = ?";

       Object[] params = new Object[] { userId };

       List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

       return roles;
   }


    
}