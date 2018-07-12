package ftc.shift.sample.services;

import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.Recipe;
import ftc.shift.sample.models.State;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.RecipeServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ftc.shift.sample.models.State.DENIED;
import static ftc.shift.sample.models.State.WAITING;

@Service
public class RecipeService implements RecipeServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public RecipeService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User provideUser(@NonNull Integer idUser) {

//        if (userRepository.getAllUsers().containsKey(idUser))
//            return userRepository.fetchUser(idUser);
//        else throw new IllegalArgumentException("Пользователя с таким логином не существует");
        return null;
    }

    private void updateUser(@NonNull User user) {

        userRepository.updateUser(user);
    }

    @Override
    public HashMap<String,Recipe> getAllMyRecipes(@NonNull Integer idUser) {
        return provideUser(idUser)
                .getMyRecipes();
    }

    @Override
    public HashMap<String,Recipe> getAllNotMyRecipes(@NonNull Integer idUser) {
        return provideUser(idUser)
                .getNotMyRecipes();
    }

    @Override
    public void addRecipeToMyRecipes(@NonNull Recipe recipe) {

//        provideUser(recipe.getIdCooker())
//                .getMyRecipes()
//                .put(recipe.getName(),recipe);
//        updateUser(userRepository.fetchUser(recipe.getIdCooker()));

    }

    @Override
    public void addRecipeToNotMyRecipes(@NonNull Recipe recipe, @NonNull Integer idUser, @NonNull Product product) {

//        provideUser(idUser)
//                .getNotMyRecipes()
//                .put(recipe.getName(),recipe);
//        provideUser(idUser)
//                .getRecipeState()
//                .put(recipe.getName(),WAITING);
//        updateUser(userRepository.fetchUser(recipe.getIdCooker()));
//
//        addUserToAllUsersForProductIdForRecipeId(recipe,idUser,product);
    }

    @Override
    public HashMap<String,Recipe> getAllRecipesForUser(@NonNull Integer idUser){

        HashMap<String,Recipe> myLenta = new HashMap<>();

//        for (Map.Entry<String,Product> myProduct : provideUser(idUser).getFridge().getProducts().entrySet()) {
//
//            for (Map.Entry<String,User> otherUser: userRepository.getAllUsers().entrySet()) {
//
//                for (Map.Entry<String,Recipe> recipeOtherUser: otherUser.getValue().getMyRecipes().entrySet()) {
//
//                    for (Product currentProduct : recipeOtherUser.getValue().getProductList()) {
//
//                        if (myProduct.getValue().equals(currentProduct)){
//
//                            myLenta.put(recipeOtherUser.getKey(),recipeOtherUser.getValue());
//                            break;
//                        }
//                    }
//                }
//            }
//        }

        return myLenta;
    }

//    @Override
//    public void addUserToAllUsersForProductIdForRecipeId(@NonNull Recipe recipe, @NonNull Integer idUser, @NonNull Product product){
//
//        getAllUsersForProductIdForRecipeId(recipe,product)
//                .put(idUser,provideUser(idUser));
//    }

    @Override
    public void changeRecipeState(Integer idUser, Recipe recipe, State newState) {

    }

    @Override
    public void removeRecipeFromMyRecipes(@NonNull Integer idUser, @NonNull Integer idRecipe) {

//        if (provideUser(idUser).getMyRecipes().containsKey(idRecipe)) {
//            provideUser(idUser).getMyRecipes().remove(idRecipe);
//            updateUser(userRepository.fetchUser(idUser));
//        }
//        else {
//            throw new IllegalArgumentException("Такого рецепта не существовало");
//        }
    }

    @Override
    public void removeRecipeFromNotMyRecipes(@NonNull Integer idUser, @NonNull Integer idRecipe) {

//        if (provideUser(idUser).getNotMyRecipes().containsKey(idRecipe)) {
//            provideUser(idUser).getNotMyRecipes().remove(idRecipe);
//            updateUser(userRepository.fetchUser(idUser));
//        }
//        else {
//            throw new IllegalArgumentException("Такого рецепта не существовало");
//        }
    }

    @Override
    public void addUserToAllUsersForProductIdForRecipeId(Recipe recipe, Integer idUser, Product product) {

    }

//    @Override
//    public void changeRecipeState(@NonNull Integer idUser,@NonNull Recipe recipe,@NonNull State newState) {
//
//        provideUser(idUser)
//                .getRecipeState()
//                .put(recipe.getIdRecipe(),newState);
//        updateUser(provideUser(idUser));
//    }

    @Override
    public ArrayList<Product> getProductsFromRecipe(@NonNull Recipe recipe) {
        return provideUser(recipe.getIdCooker())
                .getMyRecipes()
                .get(recipe.getName())
                .getProductList();
    }

    @Override
    public void addProductToMyRecipe(@NonNull Recipe recipe,@NonNull Product product) {
//
//        provideUser(recipe.getIdCooker())
//                .getMyRecipes()
//                .get(recipe.getName())
//                .getProductList()
//                .add(product);
//
//        provideUser(recipe.getIdCooker())
//                .getMyRecipes()
//                .get(recipe.getIdRecipe())
//                .getListUsersForEachProduct()
//                .put(product.getId(),new HashMap<>());
//        updateUser(provideUser(recipe.getIdCooker()));
    }

    @Override
    public void removeProductFromMyRecipe(@NonNull Recipe recipe,@NonNull Product product) {

        provideUser(recipe.getIdCooker())
                .getMyRecipes()
                .get(recipe.getName())
                .getProductList()
                .remove(product);
        updateUser(provideUser(recipe.getIdCooker()));
    }

    @Override
    public HashMap<String, String> getFinalUsersFromRecipe(@NonNull Recipe recipe) {
        return provideUser(recipe.getIdCooker())
                .getMyRecipes()
                .get(recipe.getName())
                .getFinalUserList();
    }

    @Override
    public void addUserToFinalListRecipe(Recipe recipe, Product product, Integer idUser) {

    }

//    @Override
//    public void addUserToFinalListRecipe(@NonNull Recipe recipe,@NonNull Product product,@NonNull Integer idUser) {
//
//        provideUser(recipe.getIdCooker())
//                .getMyRecipes()
//                .get(recipe.getIdRecipe())
//                .getFinalUserList()
//                .put(product.getId(),idUser);
//
//        changeRecipeState(idUser,recipe,ACCEPTED);
//        updateUser(provideUser(recipe.getIdCooker()));
//        if (getAllUsersForProductIdForRecipeId(recipe, product)==null){
//
//            //throw new IllegalArgumentException("По этому продукту нет заявок");
//        }else {
//            Set<String> set = getAllUsersForProductIdForRecipeId(recipe, product).keySet();
//            for (String entry : set) {
//
//
//                if (!idUser.equals(entry)) {
//                    deleteUserFromListForProductIdForRecipeId(recipe, product, entry);
//                    updateUser(provideUser(idUser));
//                }
//            }
//        }
//    }

    @Override
    public void removeUserFromFinalListRecipe(@NonNull Recipe recipe,@NonNull Product product,@NonNull Integer idUser) {

        provideUser(recipe.getIdCooker())
                .getMyRecipes()
                .get(recipe.getName())
                .getFinalUserList()
                .remove(product.getId(),idUser);

        updateUser(provideUser(recipe.getIdCooker()));

        changeRecipeState(idUser,recipe,WAITING);

        updateUser(provideUser(idUser));
    }

    @Override
    public HashMap<String,User> getAllUsersForProductIdForRecipeId(@NonNull Recipe recipe, @NonNull Product product) {

        HashMap<String,User> result = provideUser(recipe.getIdCooker())
                .getMyRecipes()
                .get(recipe.getName())
                .getListUsersForEachProduct().get(product.getId());
        return result;
    }

    @Override
    public void deleteUserFromListForProductIdForRecipeId(@NonNull Recipe recipe,@NonNull Product product,@NonNull Integer idUser){

        provideUser(recipe.getIdCooker())
                .getMyRecipes()
                .get(recipe.getName())
                .getListUsersForEachProduct()
                .get(product.getId())
                .remove(idUser);

        updateUser(provideUser(recipe.getIdCooker()));

        changeRecipeState(idUser,recipe,DENIED);

        updateUser(provideUser(idUser));
    }

    @Override
    public State getRecipeState(@NonNull Integer userId,@NonNull Recipe recipe) {
        return provideUser(userId).getRecipeState().get(recipe.getName());
    }
}
