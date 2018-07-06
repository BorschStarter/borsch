package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Token;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.models.UserValidInfo;

public interface UserServiceInterface {

    Token createToken(UserLogin userLogin);

    Boolean checkAccess(UserValidInfo userValidInfo);

    UserInfo provideUserInfo(String id);

    UserInfo updateUserInfo(String id, UserInfo userInfo);

    void registration(UserLogin userLogin);
}