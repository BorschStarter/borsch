package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Token;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.models.UserValidInfo;

public interface UserServiceInterface {

    Token createToken(UserLogin userLogin);

    Boolean checkAccess(UserValidInfo userValidInfo);

    //3. UserInfo provideUser(String id);

    //4. UserInfo updateUserInfo(String id, UserInfo userInfo);

    //5. void registration(UserLogin userLogin);
}
