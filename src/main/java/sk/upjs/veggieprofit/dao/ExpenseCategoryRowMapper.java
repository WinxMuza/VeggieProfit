package sk.upjs.veggieprofit.dao;

import org.springframework.jdbc.core.RowMapper;
import sk.upjs.veggieprofit.entity.ExpenseCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseCategoryRowMapper implements RowMapper<ExpenseCategory> {

    @Override
    public ExpenseCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ExpenseCategory(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("name")
        );
    }
}
