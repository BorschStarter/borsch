package ftc.shift.sample.services;

import ftc.shift.sample.models.*;

import ftc.shift.sample.repositories.interfaces.TokenRepository;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserService(final UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token createToken(UserLogin userLogin){
        Token token = new Token(userLogin.getPassword().concat(userLogin.userName));
        return tokenRepository.addToken(token,userLogin.userName);
    }

    @Override
    public Boolean checkAccess(UserValidInfo userValidInfo){

        for (Token token : tokenRepository.getTokens(userValidInfo.getId())) {
            if (token.getToken().equals(userValidInfo.getToken()))
                return true;
        }
        return false;
    }

    @Override
    public UserInfo provideUserInfo(String id){
        return provideUser(id).getUserInfo();
    }

    @Override
    public UserInfo updateUserInfo(String id, UserInfo userInfo){
        provideUser(id).setUserInfo(userInfo);
        return provideUser(id).getUserInfo();
    }

    @Override
    public void registration(UserLogin userLogin) {
        if (!userRepository.getAllUsers().containsKey(userLogin.getUserName())){
            throw new IllegalArgumentException("This login already exists");
        }
    }



    //Сверху методы для интерфейса, снизу внутренние методы
    //Добавь все желаемые методы в UserServiceInterface


    public User provideUser(String id) {

        if (userRepository.getAllUsers().containsKey(id))
            return userRepository.fetchUser(id);
        else throw new IllegalArgumentException();
    }

    public User updateUser(User user) {

        if (user == null){
            throw new IllegalArgumentException();
        }
        else {
            userRepository.updateUser(user);
            return user;
        }
    }

    public void deleteUser(String id) {
        if (userRepository.getAllUsers().containsKey(id)) {
            userRepository.deleteUser(id);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        else {
            userRepository.createUser(user);
            return user;
        }
    }
}
