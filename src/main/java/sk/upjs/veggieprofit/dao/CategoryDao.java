package sk.upjs.veggieprofit.dao;

import sk.upjs.veggieprofit.entity.Category;
import sk.upjs.veggieprofit.entity.ExpenseCategory;

import java.util.List;

public interface CategoryDao {
    List<Category> findAllByUserId(Long  userId);
    Category save(Category category);
    void update(Category category);
    void delete(Long id);
}
