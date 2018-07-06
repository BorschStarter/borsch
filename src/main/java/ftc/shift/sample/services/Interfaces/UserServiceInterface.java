package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.UserInfo;

public interface UserServiceInterface {

    //!!!Token createToken(UserLogin userLogin);

    //Boolean checkAccess(UserValidInfo userValidInfo);

    UserInfo provideUserInfo(String id);

    UserInfo updateUserInfo(String id, UserInfo userInfo);

    //5.!!! void registration(UserLogin userLogin);
}
