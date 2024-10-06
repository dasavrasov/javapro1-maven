package ru.stepup.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.stepup.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(User user) {
        jdbcTemplate.update("INSERT INTO users (username) VALUES (?)", user.getName());
    }
    public void update(User user) {
        jdbcTemplate.update("UPDATE users SET username = ? WHERE id = ?", user.getName(), user.getId());
    }

    public User findByName(String name) {
        String sql = "SELECT id,username FROM users WHERE username = ? limit 1";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new UserRowMapper());
    }

    public void delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, user.getId());
    }

    public List<User> findAll() {
        String sql = "SELECT id,username FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("username"));
            return user;
        }
    }
}
