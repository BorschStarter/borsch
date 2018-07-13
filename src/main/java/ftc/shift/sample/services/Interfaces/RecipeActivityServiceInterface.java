package ftc.shift.sample.services.Interfaces;

import lombok.NonNull;

public interface RecipeActivityServiceInterface {

    void changeRecipeStatement (@NonNull Integer userId,@NonNull Integer recipeId);

}
