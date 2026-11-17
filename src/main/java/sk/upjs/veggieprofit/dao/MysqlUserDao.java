package sk.upjs.veggieprofit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import sk.upjs.veggieprofit.entity.User;

import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class MysqlUserDao implements UserDao{

    private final JdbcTemplate jdbc;

    public MysqlUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT id, username, password_hash, created_at FROM app_user WHERE username = ?";
        List<User> users = jdbc.query(sql, new UserRowMapper(), username);
        if (users.isEmpty()) {
            return null;
        }
        return users.getFirst();
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO app_user (username, password_hash) VALUES (?,?)";
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            var ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.username());
            ps.setString(2, user.passwordHash());
            return ps;
        }, keyHolder);
            long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return user.withId(id);
    }


}
