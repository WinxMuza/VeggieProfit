package sk.upjs.veggieprofit.entity;

public record Category(Long id, Long userId, String name) {
    public Category withId(Long id){
        return new Category(id,userId, name);
    }
}
