package sk.upjs.veggieprofit.dao;

import sk.upjs.veggieprofit.entity.ExpenseCategory;

import java.util.List;

public interface ExpenseCategoryDao {
    List<ExpenseCategory> findAllByUserId(Long userId);
    ExpenseCategory findByUserIdAndName(Long userId, String name);
    ExpenseCategory save(ExpenseCategory category);
    void update(ExpenseCategory category);
    void delete(Long id);
}
