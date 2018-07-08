package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.Recipe;
import ftc.shift.sample.models.State;
import ftc.shift.sample.models.User;
import lombok.NonNull;

import java.util.ArrayList;

public interface RecipeServiceInterface {

    ArrayList<Recipe> getAllMyRecipes(@NonNull String idUser);

    ArrayList<Recipe> getAllNotMyRecipes(@NonNull String idUser);

    ArrayList<Recipe> addRecipeToMyRecipes(@NonNull Recipe recipe);

    ArrayList<Recipe> addRecipeToNotMyRecipes(@NonNull Recipe recipe);

    ArrayList<Recipe> removeRecipeFromMyRecipes(@NonNull String recipeId);

    ArrayList<Recipe> removeRecipeFromNotMyRecipes(@NonNull String recipeId);

    Recipe changeRecipeState(@NonNull String idUser,@NonNull String recipeId,@NonNull State newState);

    ArrayList<Product> getProductsFromRecipe(@NonNull String recipeId);

    Recipe addProductToMyRecipe(@NonNull String recipeId,@NonNull Product product);

    Recipe removeProductFromMyRecipe(@NonNull String recipeId,@NonNull String productId);

    ArrayList<User> getFinalUsersFromRecipe(@NonNull String recipeId);

    ArrayList<User> addUserToFinalListRecipe(@NonNull String recipeId,@NonNull String productId,@NonNull User user);

    ArrayList<User> removeUserFromFinalListRecipe(@NonNull String recipeId,@NonNull String productId,@NonNull User user);

    ArrayList<User> getAllUsersForProductIdForRecipeId(@NonNull String recipeId, @NonNull String productId);

    State getRecipeState(@NonNull String userId,@NonNull String recipeId);

    Recipe getRecipeFromMyRecipes(@NonNull String recipeId);

    Recipe getRecipeFromNotMyRecipes(@NonNull String recipeId);
}
