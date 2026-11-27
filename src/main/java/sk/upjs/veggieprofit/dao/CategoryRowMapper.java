package sk.upjs.veggieprofit.dao;

import org.springframework.jdbc.core.RowMapper;
import sk.upjs.veggieprofit.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("name")
        );
    }
}
