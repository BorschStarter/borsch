package ftc.shift.sample.services;

import ftc.shift.sample.models.*;

import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class UserService {

//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(final UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public Token createToken(UserLogin userLogin){
//        Token token = new Token(userLogin.getPassword().concat(userLogin.getId()));
//        provideUser(userLogin.getId()).getTokens().add(token);
//        return token;
//    }
//
//    public Boolean checkAccess(UserValidInfo userValidInfo){
//
//        ArrayList<Token> tokens = provideUser(userValidInfo.getId()).getTokens();
//        for (Token token : tokens) {
//            if (token.getToken().equals(userValidInfo.getToken()))
//                return true;
//        }
//        return false;
//    }
//
//    public UserInfo provideUserInfo(String id){
//        return provideUser(id).getUserInfo();
//    }
//
//    public UserInfo updateUserInfo(String id, UserInfo userInfo){
//        provideUser(id).setUserInfo(userInfo);
//        return provideUser(id).getUserInfo();
//    }

//    public User provideUser(String id) {
//
//        if (userRepository.getAllUsers().containsKey(id))
//            return userRepository.fetchUser(id);
//        else throw new IllegalArgumentException();
//    }
//
//    public User updateUser(User user) {
//
//        if (user == null){
//            throw new IllegalArgumentException();
//        }
//        else {
//            userRepository.updateUser(user);
//            return user;
//        }
//    }
//
//    public void deleteUser(String id) {
//        if (userRepository.getAllUsers().containsKey(id)) {
//            userRepository.deleteUser(id);
//        }
//        else {
//            throw new IllegalArgumentException();
//        }
//    }
//
//    public User createUser(User user) {
//        if (user == null) {
//            throw new IllegalArgumentException();
//        }
//        else {
//            userRepository.createUser(user);
//            return user;
//        }
//    }

    public void addRecipeToRecipes(User user, Recipe recipe){

        if (user == null || recipe == null){
            throw new IllegalArgumentException();
        }
        else {
            user.getRecipes().put(recipe.getId(),recipe);
        }
    }

    public void removeRecipeFromRecipes(User user, Recipe recipe){

        if (user == null || recipe == null || !user.getRecipes().containsKey(recipe.getId())){
            throw new IllegalArgumentException();
        }
        else {
            user.getRecipes().remove(recipe.getId());
        }
    }

    public Recipe getRecipeFromRecipes(User user, Recipe recipe){
        if (user == null || recipe == null || !user.getRecipes().containsKey(recipe.getId())){
            throw new IllegalArgumentException();
        }
        else {
            return user.getRecipes().get(recipe.getId());
        }
    }

    public void changeRecipeState(User user, Recipe recipe, State state){
        if (user == null || recipe == null || !user.getRecipeState().containsKey(recipe.getId())){
            throw new IllegalArgumentException();
        }
        else {
            user.getRecipeState().put(recipe.getId(),state);
        }
    }

    public State getRecipeState(User user, Recipe recipe){
        if (user == null || recipe == null || !user.getRecipeState().containsKey(recipe.getId())){
            throw new IllegalArgumentException();
        }
        else {
            return user.getRecipeState().get(recipe.getId());
        }
    }

}
