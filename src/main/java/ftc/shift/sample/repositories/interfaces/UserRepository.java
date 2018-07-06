package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.User;

import java.util.TreeMap;

public interface UserRepository {

    User fetchUser(String id);

    User updateUser(User user);

    void deleteUser(String id);

    User createUser(User user);

    TreeMap<String,User> getAllUsers();
}
