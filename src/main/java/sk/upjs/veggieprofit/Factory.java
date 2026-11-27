package sk.upjs.veggieprofit;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.veggieprofit.dao.*;
import sk.upjs.veggieprofit.entity.ExpenseCategory;
import sk.upjs.veggieprofit.service.AuthService;

public enum Factory {
    INSTANCE;

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    private AuthService authService;
    private CategoryDao categoryDao;
    private ExpenseCategoryDao expenseCategoryDao;

    public JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            var dataSource = new MysqlDataSource();
            dataSource.setUser(System.getProperty("DB_USER", "veggie"));
            dataSource.setPassword(System.getProperty("DB_PASSWORD", "veggie"));
            dataSource.setUrl(System.getProperty("DB_JDBC", "jdbc:mysql://localhost:3307/veggieprofit"));
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new MysqlUserDao(getJdbcTemplate());
        }
        return userDao;
    }

    public AuthService getAuthService() {
        if (authService == null) {
            authService = new AuthService(getUserDao());
        }
        return authService;
    }

    public CategoryDao getCategoryDao() {
        if(categoryDao == null){
            categoryDao = new MysqlCategoryDao(getJdbcTemplate());
        }
        return categoryDao;
    }

    public ExpenseCategoryDao getExpenseCategory(){
        if(expenseCategoryDao == null){
            expenseCategoryDao = new MysqlExpenseCategoryDao(getJdbcTemplate());
        }
        return expenseCategoryDao;
    }
}
