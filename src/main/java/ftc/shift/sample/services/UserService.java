package ftc.shift.sample.services;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.Controllers.StringGenerator;
import ftc.shift.sample.entity.LoginEntity;
import ftc.shift.sample.entity.UserInfoEntity;
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
    public UserInfo updateUserInfo(@NonNull UserInfo userInfo, @NonNull Integer idUser) {
        userInfo = correctUserInfo(provideUserInfo(idUser),userInfo);
        UserInfoEntity userInfoEntity = EntityProcessor.userInfoToUserInfoEntity(userInfo);
        userInfoEntity.setId(idUser);
        userInfoEntity = userRepository.updateUserInfo(userInfoEntity);
        userInfo = EntityProcessor.userInfoEntityToUserInfo(userInfoEntity);
        return userInfo;
    }



    @Override
    public void updateUserLoginInfo(@NonNull UserLogin userLogin) throws IllegalArgumentException {
        if(loginCorrectCheck(userLogin.getUserName())){
            throw new IllegalArgumentException(INCORRECT_LOGIN);
        }
        if(passwordCorrectCheck(userLogin.getPassword())){
            throw new IllegalArgumentException(INCORRECT_PASSWORD);
        }
        LoginEntity loginEntity = EntityProcessor.userLoginToLoginEntity(userLogin);
        if (userRepository.checkInitLogin(userLogin.getUserName())){
            userRepository.updateUserLoginInfo(loginEntity);
        }else{
            throw new IllegalArgumentException("Логин занят");
        }
    }



    @Override
    public void deleteUser(@NonNull Integer idUser) throws IllegalArgumentException {
        try {
            userRepository.fetchUser(idUser);
            userRepository.deleteUser(idUser);
        }catch (IllegalArgumentException ex){
            throw ex;
        }
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

    private UserInfo correctUserInfo(UserInfo baseInfo, UserInfo userInfo){
        UserInfo result = new UserInfo();

        if(userInfo.getCity()==null){
            result.setCity(baseInfo.getCity());
        }else{
            result.setCity(userInfo.getCity());
        }

        if(userInfo.getVk()==null){
            result.setVk(baseInfo.getVk());
        }else{
            result.setVk(userInfo.getVk());
        }

        if(userInfo.getCookRate()==null){
            result.setCookRate(baseInfo.getCookRate());
        }else{
            result.setCookRate(userInfo.getCookRate());
        }

        if(userInfo.getDormitory()==null){
            result.setDormitory(baseInfo.getDormitory());
        }else{
            result.setDormitory(userInfo.getDormitory());
        }

        if(userInfo.getEatRate()==null){
            result.setEatRate(baseInfo.getEatRate());
        }else{
            result.setEatRate(userInfo.getEatRate());
        }

        if(userInfo.getEmail()==null){
            result.setEmail(baseInfo.getEmail());
        }else{
            result.setEmail(userInfo.getEmail());
        }

        if(userInfo.getFirstName()==null){
            result.setFirstName(baseInfo.getFirstName());
        }else{
            result.setFirstName(userInfo.getFirstName());
        }

        if(userInfo.getSecondName()==null){
            result.setSecondName(baseInfo.getSecondName());
        }else{
            result.setSecondName(userInfo.getSecondName());
        }

        if(userInfo.getRoom()==null){
            result.setRoom(baseInfo.getRoom());
        }else{
            result.setRoom(userInfo.getRoom());
        }

        if(userInfo.getTelegram()==null){
            result.setTelegram(baseInfo.getTelegram());
        }else{
            result.setTelegram(userInfo.getTelegram());
        }

        if(userInfo.getUniversity()==null){
            result.setUniversity(baseInfo.getUniversity());
        }else{
            result.setUniversity(userInfo.getUniversity());
        }

        return result;
    }
}