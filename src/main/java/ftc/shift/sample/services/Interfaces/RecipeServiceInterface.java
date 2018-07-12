package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.Recipe;
import ftc.shift.sample.models.State;
import ftc.shift.sample.models.User;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public interface RecipeServiceInterface {

    HashMap<String,Recipe> getAllRecipesForUser(@NonNull Integer idUser);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    HashMap<String,Recipe> getAllMyRecipes(@NonNull Integer idUser);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    HashMap<String,Recipe> getAllNotMyRecipes(@NonNull Integer idUser);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    void addRecipeToMyRecipes(@NonNull Recipe recipe);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    void addRecipeToNotMyRecipes(@NonNull Recipe recipe, @NonNull Integer idUser, @NonNull Product product);

    //IllegalArgumentException("Такого рецепта не существовало")
    //IllegalArgumentException("Пользователя с таким логином не существует")
    void removeRecipeFromMyRecipes(@NonNull Integer idUser, @NonNull Integer idRecipe);

    //IllegalArgumentException("Такого рецепта не существовало")
    //IllegalArgumentException("Пользователя с таким логином не существует")
    void removeRecipeFromNotMyRecipes(@NonNull Integer idUser, @NonNull Integer idRecipe);

    void addUserToAllUsersForProductIdForRecipeId(@NonNull Recipe recipe, @NonNull Integer idUser, @NonNull Product product);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    void changeRecipeState(@NonNull Integer idUser,@NonNull Recipe recipe,@NonNull State newState);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    ArrayList<Product> getProductsFromRecipe(@NonNull Recipe recipe);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    void addProductToMyRecipe(@NonNull Recipe recipe,@NonNull Product product);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    void removeProductFromMyRecipe(@NonNull Recipe recipe,@NonNull Product product);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    HashMap<String,String> getFinalUsersFromRecipe(@NonNull Recipe recipe);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    void addUserToFinalListRecipe(@NonNull Recipe recipe,@NonNull Product product,@NonNull Integer idUser);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    void removeUserFromFinalListRecipe(@NonNull Recipe recipe,@NonNull Product product,@NonNull Integer idUser);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    HashMap<String,User> getAllUsersForProductIdForRecipeId(@NonNull Recipe recipe, @NonNull Product product);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    void deleteUserFromListForProductIdForRecipeId(@NonNull Recipe recipe,@NonNull Product product,@NonNull Integer idUser);

    //IllegalArgumentException("Пользователя с таким логином не существует")
    State getRecipeState(@NonNull Integer idUser,@NonNull Recipe recipe);
}
