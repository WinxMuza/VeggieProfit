package sk.upjs.veggieprofit.entity;

public record ExpenseCategory(Long id, Long userId, String name) {
    public ExpenseCategory withId(Long id){
        return new ExpenseCategory(id, userId, name);
    }
}
