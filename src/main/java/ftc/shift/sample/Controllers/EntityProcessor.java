package ftc.shift.sample.Controllers;

import ftc.shift.sample.entity.*;
import ftc.shift.sample.models.*;

import java.util.ArrayList;

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

    public static Product productEntityToProduct(ProductEntity productEntity){
        Product product = new Product();
        product.setFoodName(productEntity.getFoodName());
        product.setFoodId(productEntity.getFoodId());
        product.setReservedWeight(productEntity.getReservedWeight());
        product.setId(productEntity.getId());
        product.setAllWeight(productEntity.getAllWeight());
        return product;
    }
    public static ProductEntity productToProductEntity(Product product){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setFoodName(product.getFoodName());
        productEntity.setFoodId(product.getFoodId());
        productEntity.setReservedWeight(product.getReservedWeight());
        productEntity.setId(product.getId());
        productEntity.setAllWeight(product.getAllWeight());
        return productEntity;
    }

    public static RecipeInfo recipeEntitysToRecipeInfo (ArrayList<RecipeEntity> listOfRecipeEntity){
        RecipeInfo recipeInfo = new RecipeInfo();
        recipeInfo.setRecipeId(listOfRecipeEntity.get(0).getRecipeId());
        recipeInfo.setRecipeName(listOfRecipeEntity.get(0).getName());
        recipeInfo.setUserId(listOfRecipeEntity.get(0).getUserId());
        ArrayList<Integer> idProducts = new ArrayList<Integer>();
        for(RecipeEntity recipeEntity :listOfRecipeEntity){
            idProducts.add(recipeEntity.getProductId());
        }
        recipeInfo.setIdProducts(idProducts);
        return recipeInfo;
    }
    public static ArrayList<RecipeEntity> recipeInfoToRecipeEntitys (RecipeInfo recipeInfo){
        ArrayList<RecipeEntity> listOfRecipeEntitie = new ArrayList<>();
        RecipeEntity recipeEntity;
        for(Integer productId : recipeInfo.getIdProducts()){
            recipeEntity = new RecipeEntity();
            recipeEntity.setName(recipeInfo.getRecipeName());
            recipeEntity.setUserId(recipeInfo.getUserId());
            recipeEntity.setRecipeId(recipeInfo.getRecipeId());
            recipeEntity.setProductId(productId);
        }
        return listOfRecipeEntitie;
    }
}
