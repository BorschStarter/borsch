package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.User;
import ftc.shift.sample.models.UserInfo;
import lombok.NonNull;

import java.util.TreeMap;

public interface UserRepository {

    UserInfo fetchUserInfo(@NonNull String idUser);

    UserInfo updateUser(@NonNull UserInfo userInfo);

    void deleteUser(@NonNull Integer idUser);

    User createUser(@NonNull User user);

//    TreeMap<String,User> getAllUsers();
}
