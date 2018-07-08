package ftc.shift.sample.services;

import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.Recipe;
import ftc.shift.sample.models.State;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.RecipeServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

import static ftc.shift.sample.models.State.ACCEPTED;
import static ftc.shift.sample.models.State.DENIED;
import static ftc.shift.sample.models.State.WAITING;

@Service
public class RecipeService implements RecipeServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public RecipeService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User provideUser(@NonNull String idUser) {

        if (userRepository.getAllUsers().containsKey(idUser))
            return userRepository.fetchUser(idUser);
        else throw new IllegalArgumentException("Пользователя с таким логином не существует");
    }

    private User updateUser(@NonNull User user) {

        userRepository.updateUser(user);
        return user;
    }

    @Override
    public HashMap<String,Recipe> getAllMyRecipes(@NonNull String idUser) {
        return provideUser(idUser)
                .getMyRecipes();
    }

    @Override
    public HashMap<String,Recipe> getAllNotMyRecipes(@NonNull String idUser) {
        return provideUser(idUser)
                .getNotMyRecipes();
    }

    @Override
    public void addRecipeToMyRecipes(@NonNull Recipe recipe) {

        provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .put(recipe.getName(),recipe);
        updateUser(userRepository.fetchUser(recipe.getIdPovar()));
    }

    @Override
    public void addRecipeToNotMyRecipes(@NonNull Recipe recipe) {

        provideUser(recipe.getIdPovar())
                .getNotMyRecipes()
                .put(recipe.getName(),recipe);
        provideUser(recipe.getIdPovar())
                .getRecipeState()
                .put(recipe.getName(),WAITING);
        updateUser(userRepository.fetchUser(recipe.getIdPovar()));
    }

    @Override
    public void removeRecipeFromMyRecipes(@NonNull Recipe recipe) {

        if (provideUser(recipe.getIdPovar()).getMyRecipes().remove(recipe.getName(),recipe)) {
            updateUser(userRepository.fetchUser(recipe.getIdPovar()));
        }
        else {
            throw new IllegalArgumentException("Такого рецепта не существовало");
        }
    }

    @Override
    public void removeRecipeFromNotMyRecipes(@NonNull Recipe recipe) {

        if (provideUser(recipe.getIdPovar()).getNotMyRecipes().remove(recipe.getName(),recipe)) {
            updateUser(userRepository.fetchUser(recipe.getIdPovar()));
        }
        else {
            throw new IllegalArgumentException("Такого рецепта не существовало");
        }
    }

    @Override
    public void changeRecipeState(@NonNull String idUser,@NonNull Recipe recipe,@NonNull State newState) {

        provideUser(idUser)
                .getRecipeState()
                .put(recipe.getName(),newState);
        updateUser(provideUser(idUser));
    }

    @Override
    public ArrayList<Product> getProductsFromRecipe(@NonNull Recipe recipe) {
        return provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .get(recipe.getName())
                .getProductList();
    }

    @Override
    public void addProductToMyRecipe(@NonNull Recipe recipe,@NonNull Product product) {

        provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .get(recipe.getName())
                .getProductList()
                .add(product);
        updateUser(provideUser(recipe.getIdPovar()));
    }

    @Override
    public void removeProductFromMyRecipe(@NonNull Recipe recipe,@NonNull Product product) {

        provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .get(recipe.getName())
                .getProductList()
                .remove(product);
        updateUser(provideUser(recipe.getIdPovar()));
    }

    @Override
    public HashMap<String, String> getFinalUsersFromRecipe(@NonNull Recipe recipe) {
        return provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .get(recipe.getName())
                .getFinalUserList();
    }

    @Override
    public void addUserToFinalListRecipe(@NonNull Recipe recipe,@NonNull Product product,@NonNull String idUser) {

        provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .get(recipe.getName())
                .getFinalUserList()
                .put(product.getId(),idUser);

        updateUser(provideUser(recipe.getIdPovar()));

        changeRecipeState(idUser,recipe,ACCEPTED);

        updateUser(provideUser(idUser));
    }

    @Override
    public void removeUserFromFinalListRecipe(@NonNull Recipe recipe,@NonNull Product product,@NonNull String idUser) {

        provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .get(recipe.getName())
                .getFinalUserList()
                .remove(product.getId(),idUser);

        updateUser(provideUser(recipe.getIdPovar()));

        changeRecipeState(idUser,recipe,WAITING);

        updateUser(provideUser(idUser));
    }

    @Override
    public HashMap<String,User> getAllUsersForProductIdForRecipeId(@NonNull Recipe recipe, @NonNull Product product) {

        return provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .get(recipe.getName())
                .getListUsersForEachProduct()
                .get(product.getId());
    }

    @Override
    public void deleteUserFromListForProductIdForRecipeId(@NonNull Recipe recipe,@NonNull Product product,@NonNull String idUser){

        provideUser(recipe.getIdPovar())
                .getMyRecipes()
                .get(recipe.getName())
                .getListUsersForEachProduct()
                .get(product.getId())
                .remove(idUser);

        updateUser(provideUser(recipe.getIdPovar()));

        changeRecipeState(idUser,recipe,DENIED);

        updateUser(provideUser(idUser));
    }

    @Override
    public State getRecipeState(@NonNull String userId,@NonNull Recipe recipe) {
        return provideUser(userId).getRecipeState().get(recipe.getName());
    }


    /// Ниже закоменченное пока не трогать


//    public Recipe getRecipeFromRecipes(String login, String recipeId){
//
//        if(login == null || recipeId == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
//            throw new IllegalArgumentException("Рецепта с таким id не существует у данного пользователя");
//        }
//        else {
//            return userRepository.fetchUser(login).getRecipes().get(recipeId);
//        }
//    }
//
//    //3
//    public void addRecipeToRecipes(String login, Recipe recipe){
//        if(login == null || recipe == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        } else {
//            userRepository.fetchUser(login).getRecipes().put(recipe.getId(),recipe);
//        }
//    }
//
//    //5
//    public void removeRecipeFromRecipes(String login, String recipeId){
//        if(login == null || recipeId == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
//            throw new IllegalArgumentException("Вы пытаетесь удалить рецепт не у его повара");
//        }
//        else {
//            userRepository.fetchUser(login).getRecipes().remove(recipeId);
//        }
//    }
//
//    //6
//    public void changeRecipeState(String login, String recipeId, State state){
//        if(login == null || recipeId == null || state == null){
//            throw new IllegalArgumentException("Вы вели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipeState().containsKey(recipeId)){
//            throw new IllegalArgumentException("Вы пытаетесь изменить состояние по рецепту у пользователя, который никогда в нем не учавствовал");
//        }
//        else {
//            userRepository.fetchUser(login).getRecipeState().put(recipeId,state);
//        }
//    }
//
//    public State getRecipeState(String login, String recipeId){
//        if (login == null || recipeId == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipeState().containsKey(recipeId)){
//            throw new IllegalArgumentException("Вы пытаетесь получить состояние по рецепту у пользователя, который никогда в нем не учавствовал");
//        }
//        else {
//            return userRepository.fetchUser(login).getRecipeState().get(recipeId);
//        }
//    }
//
//    //login повара
//    //recipeId рецепт в его recipes
//    //7
//    public Collection<Product> getProductsFromRecipe(String login, String recipeId){
//        if(login == null || recipeId == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
//            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
//        }
//        else {
//            return userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().values();
//        }
//    }
//
//    //добавляет только повар с логином "login"
//    //8
//    public void addProductToRecipe(String login, String recipeId, Product product){
//        if(login == null || recipeId == null || product == null){
//            throw new IllegalArgumentException();
//        }
//        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
//            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
//        }
//        else {
//            userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().put(product.getId(), product);
//        }
//    }
//
//    //удаляет только повар с логином "login"
//    //9
//    public void removeProductFromRecipe(String login, String recipeId, String productId){
//        if(login == null || recipeId == null || productId == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
//            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
//        }
//        else {
//            userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().remove(productId);
//        }
//    }
//
//    //получить финальный список product<=>user
//    //10
//    public Collection<User> getUsersFromRecipe(String login, String recipeId){
//        if(login == null || recipeId == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
//            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
//        }
//        else {
//            return userRepository.fetchUser(login).getRecipes().get(recipeId).getUserList().values();
//        }
//    }
//
//    //добавить user'a в финальный список
//    //11
//    public void addUserToRecipe(String login, String recipeId, String productId, User user){
//        if(login == null || recipeId == null || user == null || productId == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
//            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
//        }
//        else {
//            userRepository.fetchUser(login).getRecipes().get(recipeId).getUserList().put(productId, user);
//        }
//    }
//
//    //удалить user'a из финального списока
//    //12
//    public void removeUserFromRecipe(String login, String recipeId, String productId, User user){
//        if(login == null || recipeId == null || user == null || productId == null){
//            throw new IllegalArgumentException("Вы ввели null");
//        }
//        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
//            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
//        }
//        else {
//            userRepository.fetchUser(login).getRecipes().get(recipeId).getUserList().remove(productId);
//        }
//    }
//

}
