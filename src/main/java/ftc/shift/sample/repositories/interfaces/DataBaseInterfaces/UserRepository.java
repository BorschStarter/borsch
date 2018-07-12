package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.LoginEntity;
import ftc.shift.sample.entity.UserInfoEntity;
import ftc.shift.sample.models.User;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import lombok.NonNull;

import java.util.TreeMap;

public interface UserRepository {

    UserInfoEntity fetchUser( Integer idUser);

    Boolean checkInitLogin( String login);

    Boolean authenticate( LoginEntity loginEntity);

    UserInfoEntity updateUserInfo( UserInfoEntity userInfoEntity);

    void updateUserLoginInfo ( LoginEntity loginEntity);

    void deleteUser( Integer idUser);

    void createUser( UserLogin userLogin);

    LoginEntity provideUserLoginInfo(String userLogin);

}
