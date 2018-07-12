package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.models.Recipe;
import lombok.NonNull;

import java.util.Collection;

public interface RecipeRepository {

    Recipe fetchRecipe( String idRecipe);

    Recipe updateRecipe( Recipe recipe);

    void deleteRecipe( String idRecipe);

    Recipe createRecipe( Recipe recipe);

    Collection<Recipe> getAllRecipe();
}
