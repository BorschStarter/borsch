package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.UserForEachProductListEntity;
import ftc.shift.sample.models.FinalRecipeUserList;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserForEachProductRepositoryEntity extends CrudRepository<UserForEachProductListEntity, Integer> {
    void createFinalRecipeUserListEntity (@NonNull Integer recipeId, @NonNull Integer productId, @NonNull Integer userId);

    ArrayList<FinalRecipeUserList> fetchFinalRecipeUserList (Integer recipeId);

    void removeFinalRecipeUserList(@NonNull Integer id);

    void removeAllFinalRecipeUserList(@NonNull Integer recipeId);
}
