package ftc.shift.sample.services;

import ftc.shift.sample.Controllers.StringGenerator;
import ftc.shift.sample.models.*;

import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.TokenRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public final class UserService implements UserServiceInterface {

    private static final String INCORRECT_LOGIN = "Логин некорректен " +
            "Ваш логин должен быть длинее 5 символов и короче 16" +
            "и состоять только из букв латинского алфавита и цифр";

    private static final String INCORRECT_PASSWORD = "Пароль некорректен " +
            "Ваш пароль должен быть длинее 5 символов и короче 16" +
            "и состоять только из букв латинского алфавита и цифр";

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserService(final UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void registration(@NonNull UserLogin userLogin) throws IllegalArgumentException{
        if(loginCorrectCheck(userLogin.getUserName())){
            throw new IllegalArgumentException(INCORRECT_LOGIN);
        }
        if(passwordCorrectCheck(userLogin.getPassword())){
            throw new IllegalArgumentException(INCORRECT_PASSWORD);
        }
        if (userRepository.checkInitLogin(userLogin.getUserName())){
            userRepository.createUser(userLogin);
        }else{
            throw new IllegalArgumentException("Логин занят");
        }

    }

    @Override
    public UserValidInfo logIn(@NonNull UserLogin userLogin) throws IllegalArgumentException{
        if(userRepository.checkInitLogin(userLogin.getUserName())){
            if(userRepository.authenticate(userLogin)){
                return startTokenSession(userLogin);
            }else{
                throw new IllegalArgumentException("Вы ввели неверный пароль.");
            }
        }else{
            throw new IllegalArgumentException("Пользователь с таким лоогином не найден.");
        }

    }

    @Override
    public void LogOut(@NonNull UserValidInfo userValidInfo) {
        tokenRepository.deleteToken(userValidInfo);
    }

    @Override
    public Boolean checkAccess(@NonNull UserValidInfo userValidInfo) throws IllegalArgumentException {
        if (userRepository.checkInitLogin(userValidInfo.getLogin())){
            try{
                if(checkTokenValid(userValidInfo)){
                    return true;
                }else{
                    return false;
                }
            }catch (IllegalArgumentException ex){
                throw ex;
            }

        }else{
            throw new IllegalArgumentException("Пользователь с таким лоогином не найден.");
        }
    }


    @Override
    public UserInfo provideUserInfo(@NonNull Integer idUser) throws IllegalArgumentException{
        try{
                return userRepository.fetchUser(idUser);
        }catch (IllegalArgumentException ex){
                throw ex;
        }
    }



    @Override
    public UserInfo updateUserInfo(@NonNull UserInfo userInfo) {
//        User user = provideUser(userInfo.getId());
//        userInfo.setEatRate(user.getUserInfo().getEatRate());
//        userInfo.setCookRate(user.getUserInfo().getCookRate());
//        user.setUserInfo(userInfo);
//        updateUser(user);
//        return provideUser(userInfo.getId()).getUserInfo();
        return null;
    }



    @Override
    public void updatePassword(@NonNull Integer idUser, @NonNull String newPassword) {

//        provideUser(idUser).setPassword(newPassword);
//        updateUser(provideUser(idUser));
    }


    private User updateUser(@NonNull User user) {

        userRepository.updateUser(user);
        return user;
    }

    @Override
    public void deleteUser(@NonNull Integer idUser) {

//        if (userRepository.getAllUsers().containsKey(idUser)) {
//            userRepository.deleteUser(idUser);
//        } else {
//            throw new IllegalArgumentException("Вы пытаетесь удалить несуществующего пользователя");
//        }
    }




    private UserValidInfo startTokenSession(@NonNull UserLogin userLogin){
        UserValidInfo userValidInfo = StringGenerator.generateUserValidInfo(userLogin.getIdUser(),userLogin.getUserName());
        return tokenRepository.addToken(userValidInfo);
    }

    private Boolean checkTokenValid(UserValidInfo userValidInfo) throws IllegalArgumentException {
        ArrayList<String> userToken = tokenRepository.getAllTokensUser(userValidInfo.getIdUser());
        if (userToken==null){
            throw new IllegalArgumentException("Нет открытых сессий у данного пользователя");
        }
        if (userToken.contains(userValidInfo.getToken())){
            return true;
        }else{
            return false;
        }
    }
    private Boolean loginCorrectCheck(String login){
        if((login==null)||(login.length()<6)||
                ((login.length()>16))){
            return false;
        }else{
            if (login.matches("[A-Za-z]+[0-9]")) {
                return true;
            } else {
                return false;
            }
        }
    }

    private Boolean passwordCorrectCheck(String password){
        if((password==null)||(password.length()<6)||
                ((password.length()>16))){
            return false;
        }else{
            if (password.matches("[A-Za-z]+[0-9]")) {
                return true;
            } else {
                return false;
            }
        }
    }

}