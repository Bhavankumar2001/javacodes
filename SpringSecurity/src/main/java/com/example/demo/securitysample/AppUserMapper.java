package com.example.demo.securitysample;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AppUserMapper implements RowMapper<AppUser> {

    public static final String BASE_SQL = "SELECT u.id, u.username, u.password FROM users u";

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer userId = rs.getInt("id");
        String userName = rs.getString("username");
        String encryptedPassword = rs.getString("password");
        return new AppUser(userId, userName, encryptedPassword);
    }
}
