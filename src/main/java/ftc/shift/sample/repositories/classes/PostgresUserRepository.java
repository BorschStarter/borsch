package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.entity.LoginEntity;
import ftc.shift.sample.entity.UserInfoEntity;
import ftc.shift.sample.models.*;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.UserRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresUserRepository implements UserRepository {

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
    public UserInfoEntity fetchUser(@NonNull final Integer idUser) throws IllegalArgumentException {
            return userRepository.findOne(idUser);
    }

    @Override
    public Boolean checkInitLogin(String login) {
        Iterable<LoginEntity> loginEntities =loginRepository.findAll();
        for(LoginEntity loginEntity: loginEntities){
            if(loginEntity.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean authenticate(LoginEntity loginEntity) {
        LoginEntity baseLoginEntity = provideUserLoginInfo(loginEntity.getLogin());
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
    public void createUser(@NonNull final UserLogin userLogin) {
        //пока не знаю как справиться с этим костылем
        UserInfo userInfo = new UserInfo(0);
        userInfo = EntityProcessor.userInfoEntityToUserInfo(
                userRepository.save(
                        EntityProcessor.userInfoToUserInfoEntity(userInfo)
        ));
        userLogin.setIdUser(userInfo.getId());
        loginRepository.save(EntityProcessor.userLoginToLoginEntity(userLogin));
    }

    @Override
    public void updateUserLoginInfo (@NonNull LoginEntity loginEntity){
        loginRepository.delete(provideUserLoginInfo(loginEntity.getLogin()).getId());
        loginRepository.save(loginEntity);
    }

    @Override
    public LoginEntity provideUserLoginInfo(String userLogin){
        Iterable<LoginEntity> list = loginRepository.findAll();
        for(LoginEntity login :list){
            if(login.getLogin().equals(userLogin)){
                return login;
            }
        }
        return null;
    }




}
