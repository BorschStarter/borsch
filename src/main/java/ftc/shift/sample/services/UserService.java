package ftc.shift.sample.services;

import ftc.shift.sample.models.*;

import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Token createToken(UserLogin userLogin) {
        return null;
    }

    @Override
    public Boolean checkAccess(UserValidInfo userValidInfo) {
        return null;
    }


    public UserInfo provideUserInfo(String id){
        return provideUser(id).getUserInfo();
    }

    public UserInfo updateUserInfo(String id, UserInfo userInfo){
        provideUser(id).setUserInfo(userInfo);
        return provideUser(id).getUserInfo();
    }

    @Override
    public void registration(UserLogin userLogin) {

    }

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





}
