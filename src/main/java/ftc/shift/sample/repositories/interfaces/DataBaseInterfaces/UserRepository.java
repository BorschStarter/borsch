package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.models.User;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import lombok.NonNull;

import java.util.TreeMap;

public interface UserRepository {

    User fetchUser(@NonNull Integer idUser);

    Boolean checkInitLogin(@NonNull String login);

    Boolean checkLoginInformation(@NonNull UserLogin userLogin);

    User updateUser(@NonNull User user);

    void deleteUser(@NonNull Integer idUser);

    User createUser(@NonNull User user);

    TreeMap<String,User> getAllUsers();
}
