package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.Recipe;

import java.util.Collection;

public interface RecipeRepository {

    Recipe fetchRecipe(String id);

    Recipe updateRecipe(Recipe recipe);

    void deleteRecipe(String id);

    Recipe createRecipe(Recipe recipe);

    Collection<Recipe> getAllRecipe();
}
