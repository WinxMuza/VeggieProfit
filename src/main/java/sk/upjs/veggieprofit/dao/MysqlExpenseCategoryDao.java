package sk.upjs.veggieprofit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import sk.upjs.veggieprofit.entity.Category;
import sk.upjs.veggieprofit.entity.ExpenseCategory;

import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class MysqlExpenseCategoryDao implements ExpenseCategoryDao{
    private final JdbcTemplate jdbc;

    public MysqlExpenseCategoryDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<ExpenseCategory> findAllByUserId(Long userId) {
        String sql = "SELECT id, user_id, name FROM expense_category WHERE user_id = ?";
        return jdbc.query(sql,new ExpenseCategoryRowMapper(),userId);
    }

    @Override
    public ExpenseCategory findByUserIdAndName(Long userId, String name) {
        String sql = "SELECT id, user_id, name FROM expense_category WHERE user_id = ? AND name = ?";
        List<ExpenseCategory> list = jdbc.query(sql, new ExpenseCategoryRowMapper(), userId, name);
        if(list.isEmpty()) return null;
        return list.getFirst();
    }

    @Override
    public ExpenseCategory save(ExpenseCategory category) {
        String sql = "INSERT INTO expense_category (user_id, name) VALUES (?, ?)";
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            var ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, category.userId());
            ps.setString(2, category.name());
            return ps;
        }, keyHolder);
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return category.withId(id);
    }

    @Override
    public void update(ExpenseCategory category) {
        String sql = "UPDATE expense_category SET name = ? WHERE id = ?";
        jdbc.update(sql, category.name(), category.id());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM expense_category WHERE id = ?";
        jdbc.update(sql, id);
    }
}
