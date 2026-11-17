package sk.upjs.veggieprofit.service;

import org.mindrot.jbcrypt.BCrypt;
import sk.upjs.veggieprofit.Session;
import sk.upjs.veggieprofit.dao.UserDao;
import sk.upjs.veggieprofit.entity.User;

public class AuthService {

    private final UserDao userDao;
    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void register (String username, String password){
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Zadajte pouzivatelske meno!");
        }
        if(password == null || password.length() < 8 ){
            throw new IllegalArgumentException("Heslo musi mat' aspon 8 znakov!");
        }
        if(userDao.findByUsername(username) != null){
            throw new IllegalArgumentException("Pouzivatelske meno uz existuje!");
        }

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        userDao.save(new User(null, username, passwordHash, null));
    }

    public void login(String username, String password){
        User user = userDao.findByUsername(username);
        if(user == null || !BCrypt.checkpw(password, user.passwordHash())){
            throw new IllegalArgumentException("Nespravvne meno alebo heslo!");
        }
        Session.INSTANCE.setCurrentUser(user);
    }
}
