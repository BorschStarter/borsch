package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.UserForEachProductList;
import lombok.NonNull;

import java.util.ArrayList;

public interface UserForEachProductListServiceInterface {
    void createUserForEachProductList(@NonNull Integer userId, @NonNull Integer productId, @NonNull Integer recipeId);

    ArrayList<UserForEachProductList> fetchUserForEachProductList(@NonNull Integer recipeId, @NonNull Integer productId);

    void deleteUserForEachProductList(@NonNull Integer id);

    void removeAllUserForEachProductList(@NonNull Integer recipeId, @NonNull Integer productId);
}
