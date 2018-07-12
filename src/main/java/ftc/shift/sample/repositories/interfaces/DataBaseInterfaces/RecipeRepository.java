package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.models.Recipe;
import lombok.NonNull;

import java.util.Collection;

public interface RecipeRepository {

    Recipe fetchRecipe(@NonNull String idRecipe);

    Recipe updateRecipe(@NonNull Recipe recipe);

    void deleteRecipe(@NonNull String idRecipe);

    Recipe createRecipe(@NonNull Recipe recipe);

    Collection<Recipe> getAllRecipe();
}
