package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.FinalRecipeUserListEntity;

import java.util.ArrayList;

public interface FinalRecipeUserListRepository {
    FinalRecipeUserListEntity createFinalRecipeUserListEntity (FinalRecipeUserListEntity finalRecipeUserListEntity);

    ArrayList<FinalRecipeUserListEntity> fetchFinalRecipeUserList (Integer recipeId);

    void removeFinalRecipeUserList(Integer id);

    void removeAllFinalRecipeUserList(Integer recipeId);
}
