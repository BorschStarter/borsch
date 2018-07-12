package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.UserInfoEntity;
import ftc.shift.sample.models.User;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import lombok.NonNull;

import java.util.TreeMap;

public interface UserRepository {

    UserInfo fetchUser(@NonNull Integer idUser);

    Boolean checkInitLogin(@NonNull String login);

    Boolean authenticate(@NonNull UserLogin userLogin);

    UserInfoEntity updateUserInfo(@NonNull UserInfoEntity userInfoEntity);

    void deleteUser(@NonNull Integer idUser);

    UserInfo createUser(@NonNull UserLogin userLogin);

}
