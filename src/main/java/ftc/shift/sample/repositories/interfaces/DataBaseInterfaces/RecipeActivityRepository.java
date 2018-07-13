package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.RecipeActivityEntity;

import java.util.ArrayList;

public interface RecipeActivityRepository {
    RecipeActivityEntity createRecipeActivityEntity (RecipeActivityEntity recipeActivityEntity);

    RecipeActivityEntity updateRecipeActivityEntity (RecipeActivityEntity recipeActivityEntity);

    ArrayList<RecipeActivityEntity> fetchRecipeActivityEntity(Integer userId);

    void removeRecipeActivityEntity(Integer userId, Integer recipeId, Integer productId);
}
