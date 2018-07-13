package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.RecipeEntity;
import java.util.ArrayList;

public interface RecipeRepository {

    ArrayList<RecipeEntity> fetchRecipe(Integer idRecipe);

    ArrayList<RecipeEntity> fetchUserRecipe(Integer idUser, Integer idRecipe);

    ArrayList<RecipeEntity> fetchActiveUserRecipe(Integer idUser);

    ArrayList<Integer> fetchAllUserRecipe(Integer idUser);

    void createRecipe(ArrayList<RecipeEntity> listOfRecipeEntity);

    void deleteRecipe(Integer idRecipe,Integer idUser);

    void addProductInRecipe(RecipeEntity recipeEntity);

    void removeProductFromUserRecipe(Integer idUser,Integer idRecipe, Integer idProduct);

}
