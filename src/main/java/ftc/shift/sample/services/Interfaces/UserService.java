package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Token;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.models.UserValidInfo;

public interface UserService {



    UserInfo provideUser(String userName, UserValidInfo userValidInfo);

    Token userLogin(UserLogin userLogin); //возвращает НОВЫЙ token user'a для сессии

    UserInfo updateUserInfo(UserValidInfo userValidInfo , UserInfo userInfo);

    void  registration(UserLogin userLogin);


}
