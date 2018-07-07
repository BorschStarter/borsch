package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Token;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.models.UserValidInfo;

public interface UserServiceInterface {

    //IllegalArgumentException("Вы передали null")
    //IllegalArgumentException("Вы ввели неправильный пароль")
    //IllegalArgumentException("Пользователя с таким логином не существует")
    Token createToken(UserLogin userLogin);

    //IllegalArgumentException("Вы передали null")
    Boolean checkAccess(UserValidInfo userValidInfo);

    //IllegalArgumentException("Вы передали null")
    UserInfo provideUserInfo(String id);

    //IllegalArgumentException("Вы передали null")
    //IllegalArgumentException("Пользователя с таким логином не существует")
    UserInfo updateUserInfo(String id, UserInfo userInfo);

    //IllegalArgumentException("Вы передали null")
    //IllegalArgumentException("Этот логин уже существует")
    void registration(UserLogin userLogin);
}
