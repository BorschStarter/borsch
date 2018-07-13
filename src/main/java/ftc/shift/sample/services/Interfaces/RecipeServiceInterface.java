package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.RecipeInfo;
import lombok.NonNull;

import java.util.ArrayList;

public interface RecipeServiceInterface {

    ArrayList<RecipeInfo> fetchAllRecipes (@NonNull Integer idUser);

    RecipeInfo fetchRecipe(@NonNull Integer recipeId);

    RecipeInfo fetchActiveRecipe(@NonNull Integer idUser);

    void createRecipe(@NonNull Integer idUser,@NonNull RecipeInfo recipeInfo);

    void removeRecipe(@NonNull Integer idRecipe,@NonNull Integer idUser);

    void addProductToRecipe(@NonNull Integer idRecipe,@NonNull Product product,@NonNull Integer idUser);

    void removeProductFromRecipe(@NonNull Integer idRecipe, @NonNull Integer idProduct, @NonNull Integer idUser);




}
