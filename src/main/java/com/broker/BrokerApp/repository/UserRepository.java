package com.broker.BrokerApp.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_USER_SQL =
            "INSERT INTO users (username, password) VALUES (:username, :password)";

    private static final String SELECT_USER_SQL =
            "SELECT COUNT(*) FROM users WHERE username = :username";

    private static final String SELECT_USER_BY_USERNAME_SQL =
            "SELECT COUNT(*) FROM users WHERE username = :username AND password = :password";

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsByUsername(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", username);

        Integer count = jdbcTemplate.queryForObject(SELECT_USER_SQL, params, Integer.class);
        return count != null && count > 0;
    }

    public void save(String username, String hashedPassword) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", username)
                .addValue("password", hashedPassword);
        jdbcTemplate.update(INSERT_USER_SQL, params);
    }

    public boolean checkPassword(String username, String password) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        params.addValue("password", password);

        Integer count = jdbcTemplate.queryForObject(SELECT_USER_BY_USERNAME_SQL, params, Integer.class);
        return count != null && count > 0;
    }
}
