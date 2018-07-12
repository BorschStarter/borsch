package ftc.shift.sample.Controllers;

import ftc.shift.sample.entity.*;
import ftc.shift.sample.models.*;

public class EntityProcessor {

    public static FoodEntity foodToFoodEntity(Food food){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setId(food.getId());
        foodEntity.setName(food.getName());
        foodEntity.setCategory(food.getCategory());
        return foodEntity;
    }
    public static Food foodEntityToFood(FoodEntity foodEntity){
        Food food = new Food();
        food.setId(foodEntity.getId());
        food.setName(foodEntity.getName());
        food.setCategory(foodEntity.getCategory());
        return food;
    }

    public static UserInfoEntity userInfoToUserInfoEntity(UserInfo userInfo){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(userInfo.getId());
        userInfoEntity.setFirstName(userInfo.getFirstName());
        userInfoEntity.setSecondName(userInfo.getSecondName());
        userInfoEntity.setCity(userInfo.getCity());
        userInfoEntity.setUniversity(userInfo.getUniversity());
        userInfoEntity.setDormitory(userInfo.getDormitory());
        userInfoEntity.setRoom(userInfo.getRoom());
        userInfoEntity.setVk(userInfo.getVk());
        userInfoEntity.setTelegram(userInfo.getTelegram());
        userInfoEntity.setEmail(userInfo.getEmail());
        userInfoEntity.setCookRate(userInfo.getCookRate());
        userInfoEntity.setEatRate(userInfo.getEatRate());
        return userInfoEntity;
    }
    public static UserInfo userInfoEntityToUserInfo(UserInfoEntity userInfoEntity){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoEntity.getId());
        userInfo.setId(userInfoEntity.getId());
        userInfo.setFirstName(userInfoEntity.getFirstName());
        userInfo.setSecondName(userInfoEntity.getSecondName());
        userInfo.setCity(userInfoEntity.getCity());
        userInfo.setUniversity(userInfoEntity.getUniversity());
        userInfo.setDormitory(userInfoEntity.getDormitory());
        userInfo.setRoom(userInfoEntity.getRoom());
        userInfo.setVk(userInfoEntity.getVk());
        userInfo.setTelegram(userInfoEntity.getTelegram());
        userInfo.setEmail(userInfoEntity.getEmail());
        userInfo.setCookRate(userInfoEntity.getCookRate());
        userInfo.setEatRate(userInfoEntity.getEatRate());
        return userInfo;
    }

    public static LoginEntity userLoginToLoginEntity(UserLogin userLogin){
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUserId(userLogin.getIdUser());
        loginEntity.setLogin(userLogin.getUserName());
        loginEntity.setPassword(userLogin.getPassword());
        return loginEntity;
    }

}
