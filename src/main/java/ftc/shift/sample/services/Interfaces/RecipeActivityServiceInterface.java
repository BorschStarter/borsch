package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.RecipeActivity;
import lombok.NonNull;

import java.util.ArrayList;

public interface RecipeActivityServiceInterface {

    void changeRecipeStatement (@NonNull Integer userId, @NonNull Integer recipeId, @NonNull Integer productId, @NonNull Boolean state);

    RecipeActivity createRecipeActivityEntity (@NonNull Integer userId, @NonNull Integer recipeId, @NonNull Integer productId);

    ArrayList<RecipeActivity> fetchRecipeActivityEntity(@NonNull Integer userId);

    void removeRecipeActivityEntity(@NonNull Integer userId, @NonNull Integer recipeId, @NonNull Integer productId);

}
