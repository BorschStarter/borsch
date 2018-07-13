package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.FinalRecipeUserList;
import lombok.NonNull;

import java.util.ArrayList;

public interface FinalRecipeUserListServiceInterface {
    void createFinalRecipeUserListEntity (@NonNull Integer recipeId, @NonNull Integer productId, @NonNull Integer userId);

    ArrayList<FinalRecipeUserList> fetchFinalRecipeUserList (@NonNull Integer recipeId);

    void removeFinalRecipeUserList(@NonNull Integer id);

    void removeAllFinalRecipeUserList(@NonNull Integer recipeId);
}
