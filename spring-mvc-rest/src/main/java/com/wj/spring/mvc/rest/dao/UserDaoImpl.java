package com.wj.spring.mvc.rest.dao;

import com.wj.spring.mvc.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author : wangjia
 * @time : 2017/12/25 23:39
 */
public class UserDaoImpl implements UserDao {
    private static final String INS = " INSERT INTO user(username,password) VALUES (?,?) ";
    private static final String GET = " SELECT uid,username,password FROM user WHERE username = ? AND password = ? ";
    private static final String EXIST = " SELECT 1 FROM user WHERE username = ? LIMIT 1 ";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void ins(User user) {
        jdbcTemplate.update(INS, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existName(String username) {
        try {
            Integer res = jdbcTemplate.queryForObject(EXIST, Integer.class, username);
            return res >= 1;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public Optional<User> get(String username, String password) {
        try {
            User user = jdbcTemplate.queryForObject(GET, new UserRowMapper(), username, password);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(
                    resultSet.getLong("uid"),
                    resultSet.getString("username"),
                    resultSet.getString("password")
            );
        }
    }
}
