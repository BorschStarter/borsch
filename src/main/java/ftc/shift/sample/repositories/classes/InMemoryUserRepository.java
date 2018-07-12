package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.entity.LoginEntity;
import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.entity.UserInfoEntity;
import ftc.shift.sample.models.*;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.UserRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {

    @Autowired
    private UserRepositoryEntity userRepository;
    @Autowired
    private LoginRepositoryEntity loginRepository;
    @Autowired
    private FridgeRepositoryEntity fridgeRepository;
    @Autowired
    private ProductRepositoryEntity productRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodRepositoryEntity foodRepositoryEntity;

    public Integer getFoodId(String foodName){
        for (Food food: foodRepository.getAllFoods().values()){
            if(food.getName().equals(foodName)){
                return food.getId();
            }
        }
        return null;
    }

    @Override
    public UserInfo fetchUser(@NonNull final Integer idUser) throws IllegalArgumentException {
        //todo Вынести проверки и приведения в логику
        UserInfo userInfo = EntityProcessor.userInfoEntityToUserInfo(userRepository.findOne(idUser));
        if(userInfo==null){
            throw new IllegalArgumentException("Пользователь не найден");
        }else{
            return userInfo;
        }
    }

    @Override
    public Boolean checkInitLogin(String login) {
        Iterable<LoginEntity> loginEntities =loginRepository.findAll();
        for(LoginEntity loginEntity: loginEntities){
            if(loginEntity.getLogin().equals(login)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean authenticate(LoginEntity loginEntity) {
        LoginEntity baseLoginEntity = provideUserLoginInfo(loginEntity.getUserId());
        if((baseLoginEntity.getLogin().equals(loginEntity.getLogin()))&&
                (baseLoginEntity.getPassword().equals(loginEntity.getPassword()))) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public UserInfoEntity updateUserInfo(@NonNull  UserInfoEntity userInfoEntity) {
        return userRepository.save(userInfoEntity);
    }

    @Override
    public void deleteUser(@NonNull final Integer iduser) {
        //ToDo После всего функционала написать БЕЗОПАСНОЕ удаление юзера
    }

    @Override
    public UserInfo createUser(@NonNull final UserLogin userLogin) {
        UserInfo userInfo = new UserInfo();
        userInfo = EntityProcessor.userInfoEntityToUserInfo(
                userRepository.save(
                        EntityProcessor.userInfoToUserInfoEntity(userInfo)
        ));
        userLogin.setIdUser(userInfo.getId());
        loginRepository.save(EntityProcessor.userLoginToLoginEntity(userLogin));

        return userInfo;
    }

    @Override
    public void updateUserLoginInfo (@NonNull LoginEntity loginEntity){
        loginRepository.delete(provideUserLoginInfo(loginEntity.getUserId()).getId());
        loginRepository.save(loginEntity);
    }

    private LoginEntity provideUserLoginInfo(Integer idUser){
        Iterable<LoginEntity> list = loginRepository.findAll();
        for(LoginEntity login :list){
            if(login.getUserId().equals(idUser)){
                return login;
            }
        }
        return null;
    }




}
