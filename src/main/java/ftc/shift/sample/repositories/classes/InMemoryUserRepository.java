package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.models.*;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private TreeMap<String, User> userCache = new TreeMap<>();

    public InMemoryUserRepository() {

            userCache.put("1", new User(new UserInfo("1","1","1","1","1","1","1","1","1","YEPY",0.1,0.2), new Fridge(), "1","2", null,
                    null));
            userCache.put("2", new User(new UserInfo("1","1","1","1","1","1","1","1","1","YEPY",0.1,0.2), new Fridge(), "2","2", null,
                    null));

    }


    @Override
    public User fetchUser(final String login) {
        return userCache.get(login);
    }

    @Override
    public User updateUser(final User user) {
        userCache.put(user.getLogin(), user);
        return user;
    }

    @Override
    public void deleteUser(final String login) {
        userCache.remove(login);
    }

    @Override
    public User createUser(final User user) {
        user.setLogin(user.getLogin());
        userCache.put(user.getLogin(), user);
        return user;
    }

    @Override
    public TreeMap<String,User> getAllUsers() {
        return userCache;
    }
}
