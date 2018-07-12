package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.models.UserValidInfo;
import lombok.NonNull;

public interface UserServiceInterface {
    UserValidInfo logIn(@NonNull UserLogin userLogin) throws IllegalArgumentException;

    void LogOut(@NonNull UserValidInfo userValidInfo);

    Boolean checkAccess(@NonNull UserValidInfo userValidInfo);

    UserInfo provideUserInfo(@NonNull Integer idUser);

    UserInfo updateUserInfo(@NonNull UserInfo userInfo, @NonNull Integer idUser);

    void registration(@NonNull UserLogin userLogin);

    void deleteUser(@NonNull Integer idUser);

    void updateUserLoginInfo(@NonNull UserLogin userLogin);
}
