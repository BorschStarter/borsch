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

import static ftc.shift.sample.services.ServiceResources.*;

@Service
public final class UserService implements UserServiceInterface {


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
            throw new IllegalArgumentException(INCORRECT_LOGIN_FIELD);
        }
        if(passwordCorrectCheck(userLogin.getPassword())){
            throw new IllegalArgumentException(INCORRECT_PASSWORD_FIELD);
        }
        if (!userRepository.checkInitLogin(userLogin.getUserName())){
            userRepository.createUser(userLogin);
        }else{
            throw new IllegalArgumentException(LOGIN_IS_RESERVED);
        }

    }

    @Override
    public UserValidInfo logIn(@NonNull UserLogin userLogin) throws IllegalArgumentException{

        if(userRepository.checkInitLogin(userLogin.getUserName())){
            userLogin.setIdUser(userRepository.provideUserLoginInfo(userLogin.getUserName()).getUserId());
            LoginEntity loginEntity = EntityProcessor.userLoginToLoginEntity(userLogin);

            if(userRepository.authenticate(loginEntity)){
                return startTokenSession(userLogin);
            }else{
                throw new IllegalArgumentException(INCORRECT_PASSWORD);
            }
        }else{
            throw new IllegalArgumentException(CANT_FIND_USER_WITH_THAT_LOGIN);
        }

    }

    @Override
    public void LogOut(@NonNull UserValidInfo userValidInfo) {
        tokenRepository.deleteToken(userValidInfo);
    }

    @Override
    public Boolean checkAccess(@NonNull UserValidInfo userValidInfo) throws IllegalArgumentException {
        if (userRepository.checkInitLogin(userValidInfo.getLogin())) {
            try {
                return (checkTokenValid(userValidInfo));
            } catch (IllegalArgumentException ex) {
                throw ex;
            }
        } else{
            throw new IllegalArgumentException(CANT_VALID_USER_WITH_THAT_LOGIN);
        }
    }

    @Override
    public UserInfo provideUserInfo(@NonNull Integer idUser) throws IllegalArgumentException{
        UserInfoEntity userInfoEntity =userRepository.fetchUser(idUser);
        if(userInfoEntity==null){
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }else{
            return EntityProcessor.userInfoEntityToUserInfo(userInfoEntity);
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
            throw new IllegalArgumentException(INCORRECT_LOGIN_FIELD);
        }
        if(passwordCorrectCheck(userLogin.getPassword())){
            throw new IllegalArgumentException(INCORRECT_PASSWORD_FIELD);
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
        if (userRepository.fetchUser(idUser)==null){
            throw new IllegalArgumentException(CANT_FIND_USER_WITH_THAT_LOGIN);
        }else {
            userRepository.deleteUser(idUser);
        }
    }




    private UserValidInfo startTokenSession(@NonNull UserLogin userLogin){
        UserValidInfo userValidInfo = StringGenerator.generateUserValidInfo(userLogin.getIdUser(),userLogin.getUserName());
        return tokenRepository.addToken(userValidInfo);
    }

    private Boolean checkTokenValid(UserValidInfo userValidInfo) throws IllegalArgumentException {
        ArrayList<String> userToken = tokenRepository.getAllTokensUser(userValidInfo.getIdUser());
        if (userToken.isEmpty()){
            throw new IllegalArgumentException(NO_TOKEN_FOR_USER);
        }
        for(String token : userToken){
            if (token.equals(userValidInfo.getToken())){
                return true;
            }
        }
        return false;
    }

    private Boolean loginCorrectCheck(String login){
        if((login==null)||(login.length()<6)||
                ((login.length()>16))){
            return false;
        }else{
            return login.matches("[A-Za-z]+[0-9]");
        }
    }

    private Boolean passwordCorrectCheck(String password){
        if((password==null)||(password.length()<6)||
                ((password.length()>16))){
            return false;
        }else{
            return password.matches("[A-Za-z]+[0-9]");
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

        if(userInfo.getDormitory()==null){
            result.setDormitory(baseInfo.getDormitory());
        }else{
            result.setDormitory(userInfo.getDormitory());
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

        //Запрещенные к самостоятельному изменению поля
            result.setCookRate(baseInfo.getCookRate());
            result.setEatRate(baseInfo.getEatRate());

        return result;
    }
}