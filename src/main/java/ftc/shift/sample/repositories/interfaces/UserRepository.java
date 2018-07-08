package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.User;
import lombok.NonNull;

import java.util.TreeMap;

public interface UserRepository {

    User fetchUser(@NonNull String idUser);

    User updateUser(@NonNull User user);

    void deleteUser(@NonNull String idUser);

    User createUser(@NonNull User user);

    TreeMap<String,User> getAllUsers();
}
