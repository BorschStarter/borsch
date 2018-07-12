package ftc.shift.sample.services;

import ftc.shift.sample.models.*;

import ftc.shift.sample.repositories.interfaces.TokenRepository;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Random;

@Service
public final class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserService(final UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    private String generateToken(@NonNull String characters) {

        Random random = new Random();
        char[] text = new char[characters.length()];
        for (int i = 0; i < characters.length(); i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }


    public Integer getUserByLogin(String login){
        return userRepository.getAllUsers().get(login).getUserInfo().getId();

    }

    @Override
    public UserValidInfo createToken(@NonNull UserLogin userLogin) {
    userLogin.setIdUser(getUserByLogin(userLogin.getUserName()));
        if (!provideUser(userLogin.getIdUser()).getPassword().equals(userLogin.getPassword())) {
            throw new IllegalArgumentException("Вы ввели неправильный пароль");
        }

        UserValidInfo userValidInfo = new UserValidInfo();
        userValidInfo.setToken(generateToken(userLogin.getPassword().concat(userLogin.userName)));
        userValidInfo.setLogin(userLogin.getUserName());
        userValidInfo.setIdUser(userLogin.getIdUser());
        tokenRepository.addToken(userValidInfo);
        return userValidInfo;
    }

    @Override
    public void deleteToken(@NonNull UserValidInfo userValidInfo) {
//
//        if (userRepository.getAllUsers().containsKey(userValidInfo.getIdUser())) {
//            if (tokenRepository.getAllTokensUser(userValidInfo.getIdUser()).contains(userValidInfo.getToken())) {
//                tokenRepository.deleteToken(userValidInfo);
//            } else {
//                throw new IllegalArgumentException("Токена с таким пользователем не существует");
//            }
//        } else {
//            throw new IllegalArgumentException("Пользователя с таким логином не существует");
//        }
    }

    @Override
    public Boolean checkAccess(@NonNull UserValidInfo userValidInfo) {
//
//        for (String token : tokenRepository.getAllTokensUser(userValidInfo.getId())) {
//            if (token.equals(userValidInfo.getToken()))
//                return true;
//        }
         return false;
    }

    @Override
    public UserInfo provideUserInfo(@NonNull Integer idUser) {

        return provideUser(idUser).getUserInfo();
    }

    @Override
    public UserInfo updateUserInfo(@NonNull UserInfo userInfo) {

        provideUser(userInfo.getId()).setUserInfo(userInfo);
        return updateUser(provideUser(userInfo.getId())).getUserInfo();
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
}