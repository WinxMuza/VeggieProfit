package sk.upjs.veggieprofit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import sk.upjs.veggieprofit.entity.Category;
import sk.upjs.veggieprofit.entity.ExpenseCategory;

import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class MysqlCategoryDao implements CategoryDao{

    private final JdbcTemplate jdbc;

    public MysqlCategoryDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Category> findAllByUserId(Long userId) {
        String sql="SELECT id, user_id, name FROM category WHERE user_id = ? ORDER BY name";
        return jdbc.query(sql,new CategoryRowMapper(),userId);
    }

    @Override
    public Category save(Category category) {
        String sql = "INSERT INTO category (user_id, name) VALUES (?,?)";
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            var ps= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,category.userId());
            ps.setString(2, category.name());
            return ps;
        }, keyHolder);
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return category.withId(id);
    }

    @Override
    public void update(Category category) {
        String sql ="UPDATE category SET name = ? WHERE id = ?";
        jdbc.update(sql,category.name(),category.id());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM category WHERE id = ?";
        jdbc.update(sql,id);
    }
}
