package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Token;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.models.UserValidInfo;

public interface UserServiceInterface {

    UserInfo provideUserInfo(String id);

    UserInfo updateUserInfo(String id, UserInfo userInfo);

    //Token createToken(UserLogin userLogin);

    //Boolean checkAccess(UserValidInfo userValidInfo);

    //5. void registration(UserLogin userLogin);
}
