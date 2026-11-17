package sk.upjs.veggieprofit;

import sk.upjs.veggieprofit.entity.User;

public enum Session {
    INSTANCE;

    private User currentUser;

    public User getCurrentUser(){
        return currentUser;
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public void logout(){
        currentUser = null;
    }
}
