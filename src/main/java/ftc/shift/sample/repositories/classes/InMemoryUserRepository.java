package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private TreeMap<String, User> userCache = new TreeMap<>();

    @Override
    public User fetchUser(@NonNull final String idUser) {
        return userCache.get(idUser);
    }

    @Override
    public User updateUser(@NonNull final User user) {
        userCache.put(user.getLogin(), user);
        return user;
    }

    @Override
    public void deleteUser(@NonNull final String iduser) {
        userCache.remove(iduser);
    }

    @Override
    public User createUser(@NonNull final User user) {
        userCache.put(user.getLogin(), user);
        return user;
    }

    @Override
    public TreeMap<String,User> getAllUsers() {
        return userCache;
    }
}
