package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.RecipeEntity;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.Recipe;
import lombok.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;

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
