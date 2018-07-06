package ftc.shift.sample.services;

import ftc.shift.sample.models.*;

import ftc.shift.sample.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class Pickles {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Расчленить user'a в аргуметах метода (updateUser)
    //Легальность запрса, сравнение Логин-токен
    //прверка пары Логин-пароль везде


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
    } //User->UserInfo

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

    public void addProductToFridge(User user, Product product){ //User->userID

        if (user == null || product == null){
            throw new IllegalArgumentException();
        }
        else {
            user.getFridge().getProducts().put(product.getId(),product);
        }
    }

    public void removeProductFromFridge(User user, Product product){ //User->userID product->productID

        if (user == null || product == null || !user.getFridge().getProducts().containsKey(product.getId())){
            throw new IllegalArgumentException();
        }
        else {
            user.getFridge().getProducts().remove(product.getId());
        }
    }

    public Product getProductFromFridge(User user, Product product){ //?
        if (user == null || product == null || !user.getFridge().getProducts().containsKey(product.getId())){
            throw new IllegalArgumentException();
        }
        else {
            return user.getFridge().getProducts().get(product.getId());
        }
    }

    //////////////////////////////////////////////
    public Recipe getRecipeFromRecipes(String login, String recipeId){
        if(login == null || recipeId == null || !userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException();
        } else {
            return userRepository.fetchUser(login).getRecipes().get(recipeId);
        }
    }

    public void addRecipeToRecipes(String login, Recipe recipe){
        if(login == null || recipe == null){
            throw new IllegalArgumentException();
        } else {
            userRepository.fetchUser(login).getRecipes().put(recipe.getId(),recipe);
        }
    }

    public void removeRecipeFromRecipes(String login, String recipeId){
        if(login == null || recipeId == null || !userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException();
        } else {
            userRepository.fetchUser(login).getRecipes().remove(recipeId);
        }
    }

    public void changeRecipeState(String login, String recipeId, State state){
        if(login == null || recipeId == null || !userRepository.fetchUser(login).getRecipeState().containsKey(recipeId)){
            throw new IllegalArgumentException();
        } else {
            userRepository.fetchUser(login).getRecipeState().put(recipeId,state);

        }
    }

    public State getRecipeState(String login, String recipeId){
        if (login == null || recipeId == null || !userRepository.fetchUser(login).getRecipeState().containsKey(recipeId)){
            throw new IllegalArgumentException();
        } else {
            return userRepository.fetchUser(login).getRecipeState().get(recipeId);
        }
    }

    public Collection<Product> getProductsFromRecipe(String login, String recipeId){
        if(login == null || recipeId == null || !userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException();
        } else {
            return userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().values();
        }
    }

    public void addProductToRecipe(String login, String recipeId, Product product){
        if(login == null || recipeId == null || product == null || !userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException();
        } else {
            userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().put(product.getId(), product);
        }
    }

    public void removeProductFromRecipe(String login, String recipeId, String productId){
        if(login == null || recipeId == null || productId == null || !userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException();
        } else {
            userRepository.fetchUser(login).getRecipes().get(recipeId).getProductList().remove(productId);
        }
    }


    public Collection<User> getUsersFromRecipe(String login, String recipeId){
        if(login == null || recipeId == null || !userRepository.fetchUser(login).getRecipes().containsKey(recipeId)){
            throw new IllegalArgumentException();
        } else {
            return userRepository.fetchUser(login).getRecipes().get(recipeId).getUserList().values();
        }
    }
}
