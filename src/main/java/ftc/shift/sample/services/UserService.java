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

@Service
public final class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserService(final UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }


//    public Integer getUserByLogin(String login){
//        return userRepository.getAllUsers().get(login).getUserInfo().getId();
//
//    }



    @Override
    public UserValidInfo logIn(@NonNull UserLogin userLogin) throws IllegalArgumentException{
        if(userRepository.checkInitLogin(userLogin.getUserName())){
            if(userRepository.checkLoginInformation(userLogin)){
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
    public UserInfo provideUserInfo(@NonNull Integer idUser) {

        return provideUser(idUser).getUserInfo();
    }

    @Override
    public UserInfo updateUserInfo(@NonNull UserInfo userInfo) {
        User user = provideUser(userInfo.getId());
        userInfo.setEatRate(user.getUserInfo().getEatRate());
        userInfo.setCookRate(user.getUserInfo().getCookRate());
        user.setUserInfo(userInfo);
        updateUser(user);
        return provideUser(userInfo.getId()).getUserInfo();
    }

    @Override
    public void registration(@NonNull UserLogin userLogin) {
        User user = new User();
        user.setLogin(userLogin.getUserName());
        user.setPassword(userLogin.getPassword());
        userRepository.createUser(user);

    }

    @Override
    public void updatePassword(@NonNull Integer idUser, @NonNull String newPassword) {

        provideUser(idUser).setPassword(newPassword);
        updateUser(provideUser(idUser));
    }

    private User provideUser(@NonNull Integer idUser) {

       // if (userRepository.getAllUsers().containsKey(idUser))
            return userRepository.fetchUser(idUser);
       // else throw new IllegalArgumentException("Пользователя с таким логином не существует");
    }

    private User updateUser(@NonNull User user) {

        userRepository.updateUser(user);
        return user;
    }

    @Override
    public void deleteUser(@NonNull Integer idUser) {

        if (userRepository.getAllUsers().containsKey(idUser)) {
            userRepository.deleteUser(idUser);
        } else {
            throw new IllegalArgumentException("Вы пытаетесь удалить несуществующего пользователя");
        }
    }

    private void createUser(@NonNull User user) {

        userRepository.createUser(user);
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

}