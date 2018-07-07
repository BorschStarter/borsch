package ftc.shift.sample.services;

import ftc.shift.sample.models.*;

import ftc.shift.sample.repositories.interfaces.TokenRepository;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserService(final UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    private String generateToken(String characters) {

        Random random = new Random();
        char[] text = new char[characters.length()];
        for (int i = 0; i < characters.length(); i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    @Override
    public Token createToken(UserLogin userLogin){

        if (userLogin == null){
            throw new IllegalArgumentException("Вы передали null");
        }
        else {
            if (provideUser(userLogin.userName).getPassword().equals(userLogin.getPassword())){
                throw new IllegalArgumentException("Вы ввели неправильный пароль");
            }
        }

        Token token = new Token();
        token.setToken(generateToken(userLogin.getPassword().concat(userLogin.userName)));
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

        if (id == null){
            throw new IllegalArgumentException("Вы передали null");
        }

        return provideUser(id).getUserInfo();
    }

    @Override
    public UserInfo updateUserInfo(String id, UserInfo userInfo){

        if (id == null || userInfo == null){
            throw new IllegalArgumentException("Вы передали null");
        }

        provideUser(id).setUserInfo(userInfo);
        return provideUser(id).getUserInfo();
    }

    @Override
    public void registration(UserLogin userLogin) {
        if (!userRepository.getAllUsers().containsKey(userLogin.getUserName())){
            throw new IllegalArgumentException("This login already exists");
        }
        else {
            User user = new User();
            user.setLogin(userLogin.getUserName());
            user.setPassword(userLogin.getPassword());
            user.getUserInfo().setId(userLogin.getUserName());
            createUser(user);
        }
    }



    //Сверху методы для интерфейса, снизу внутренние методы
    //Добавь все желаемые методы в UserServiceInterface


    private User provideUser(String id) {

        if (id == null){
            throw new IllegalArgumentException("Вы ввели null");
        }

        if (userRepository.getAllUsers().containsKey(id))
            return userRepository.fetchUser(id);
        else throw new IllegalArgumentException("Пользователя с таким логином не существует");
    }

    private User updateUser(User user) {

        if (user == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else {
            userRepository.updateUser(user);
            return user;
        }
    }

    private void deleteUser(String id) {

        if (id == null){
            throw new IllegalArgumentException("Вы ввели null");
        }

        if (userRepository.getAllUsers().containsKey(id)) {
            userRepository.deleteUser(id);
        }
        else {
            throw new IllegalArgumentException("Вы пытаетесь удалить несуществующего пользователя");
        }
    }

    private void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Вы ввели null");
        }
        else {
            userRepository.createUser(user);
        }
    }

    public Recipe getRecipeFromRecipes(String login, String recipeId){

        if(login == null || recipeId == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException("Рецепта с таким id не существует у данного пользователя");
        }
        else {
            return userRepository.fetchUser(login).getRecipes().get(recipeId);
        }
    }

    public void addRecipeToRecipes(String login, Recipe recipe){
        if(login == null || recipe == null){
            throw new IllegalArgumentException("Вы ввели null");
        } else {
            userRepository.fetchUser(login).getRecipes().put(recipe.getId(),recipe);
        }
    }

    public void removeRecipeFromRecipes(String login, String recipeId){
        if(login == null || recipeId == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException("Вы пытаетесь удалить рецепт не у его повара");
        }
        else {
            userRepository.fetchUser(login).getRecipes().remove(recipeId);
        }
    }

    public void changeRecipeState(String login, String recipeId, State state){
        if(login == null || recipeId == null || state == null){
            throw new IllegalArgumentException("Вы вели null");
        }
        else if (!userRepository.fetchUser(login).getRecipeState().containsKey(recipeId)){
            throw new IllegalArgumentException("Вы пытаетесь изменить состояние по рецепту у пользователя, который никогда в нем не учавствовал");
        }
        else {
            userRepository.fetchUser(login).getRecipeState().put(recipeId,state);
        }
    }

    public State getRecipeState(String login, String recipeId){
        if (login == null || recipeId == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!userRepository.fetchUser(login).getRecipeState().containsKey(recipeId)){
            throw new IllegalArgumentException("Вы пытаетесь получить состояние по рецепту у пользователя, который никогда в нем не учавствовал");
        }
        else {
            return userRepository.fetchUser(login).getRecipeState().get(recipeId);
        }
    }

    //login повара
    //recipeId рецепт в его recipes
    public Collection<Product> getProductsFromRecipe(String login, String recipeId){
        if(login == null || recipeId == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
        }
        else {
            return userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().values();
        }
    }

    //добавляет только повар с логином "login"
    public void addProductToRecipe(String login, String recipeId, Product product){
        if(login == null || recipeId == null || product == null){
            throw new IllegalArgumentException();
        }
        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
        }
        else {
            userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().put(product.getId(), product);
        }
    }

    //удаляет только повар с логином "login"
    public void removeProductFromRecipe(String login, String recipeId, String productId){
        if(login == null || recipeId == null || productId == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
        }
        else {
            userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().remove(productId);
        }
    }

    //получить финальный список product<=>user
    public Collection<User> getUsersFromRecipe(String login, String recipeId){
        if(login == null || recipeId == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
        }
        else {
            return userRepository.fetchUser(login).getRecipes().get(recipeId).getUserList().values();
        }
    }

    //добавить user'a в финальный список
    public void addUserToRecipe(String login, String recipeId, String productId, User user){
        if(login == null || recipeId == null || user == null || productId == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
        }
        else {
            userRepository.fetchUser(login).getRecipes().get(recipeId).getUserList().put(productId, user);
        }
    }

    //удалить user'a из финального списока
    public void removeUserFromRecipe(String login, String recipeId, String productId, User user){
        if(login == null || recipeId == null || user == null || productId == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException("Такого рецепта нет у данного пользователя");
        }
        else {
            userRepository.fetchUser(login).getRecipes().get(recipeId).getUserList().remove(productId);
        }
    }
}