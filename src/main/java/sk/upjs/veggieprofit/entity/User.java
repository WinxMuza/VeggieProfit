package sk.upjs.veggieprofit.entity;

import java.time.LocalDateTime;

public record User(Long id, String username, String passwordHash, LocalDateTime createdAt){

    public User withId(Long id){
        return new User(id, username, passwordHash, createdAt);
    }

}
