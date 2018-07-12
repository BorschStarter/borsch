package ftc.shift.sample.services;

import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.Recipe;
import ftc.shift.sample.models.RecipeInfo;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.RecipeRepository;
import ftc.shift.sample.services.Interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecipeService implements RecipeServiceInterface {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public ArrayList<RecipeInfo> fetchAllRecipes(Integer idUser) {
        return null;
    }

    @Override
    public Recipe fetchRecipe(Integer idRecipe) {
        return null;
    }

    @Override
    public ArrayList<Integer> fetchEatersId(Integer idRecipe) {
        return null;
    }

    @Override
    public Recipe fetchActiveRecipe(Integer idUser) {
        return null;
    }

    @Override
    public RecipeInfo createRecipe(Integer idUser, String recipeName) {
        return null;
    }

    @Override
    public void removeRecipe(Integer idRecipe) {

    }

    @Override
    public void addProductToRecipe(Integer idRecipe, Product product) {

    }

    @Override
    public void removeProductFromRecipe(Integer idRecipe, Integer idProduct) {

    }
}
