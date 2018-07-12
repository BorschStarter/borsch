package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.Recipe;
import ftc.shift.sample.models.RecipeInfo;
import ftc.shift.sample.models.State;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public interface RecipeServiceInterface {

    ArrayList<RecipeInfo> fetchAllRecipes (@NonNull Integer idUser);

    Recipe fetchRecipe(@NonNull Integer idRecipe);

    ArrayList<Integer> fetchEatersId(@NonNull Integer idRecipe);

    Recipe fetchActiveRecipe(@NonNull Integer idUser);

    RecipeInfo createRecipe(@NonNull Integer idUser,@NonNull String recipeName);

    void removeRecipe(@NonNull Integer idRecipe);

    void addProductToRecipe(@NonNull Integer idRecipe,@NonNull Product product);

    void removeProductFromRecipe(@NonNull Integer idRecipe, @NonNull Integer idProduct);




}
