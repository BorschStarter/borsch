package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.RecipeEntity;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.RecipeRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.RecipeRepositoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class PostgresRecipeRepository implements RecipeRepository {

    @Autowired
    private RecipeRepositoryEntity recipeService;

    @Override
    public ArrayList<RecipeEntity> fetchRecipe(Integer idRecipe) {
        Iterable<RecipeEntity> listOfRecipeEntitie = recipeService.findAll();
        ArrayList<RecipeEntity> result = new ArrayList<>();
        for(RecipeEntity recipeEntity:listOfRecipeEntitie){
            if (recipeEntity.getRecipeId().equals(idRecipe)){
                result.add(recipeEntity);
            }
        }
        return result;
    }

    @Override
    public ArrayList<RecipeEntity> fetchUserRecipe(Integer idUser, Integer idRecipe) {
        Iterable<RecipeEntity> listOfRecipeEntitie = recipeService.findAll();
        ArrayList<RecipeEntity> result = new ArrayList<>();
        for(RecipeEntity recipeEntity:listOfRecipeEntitie){
            if((recipeEntity.getUserId().equals(idUser))&&
                    (recipeEntity.getRecipeId().equals(idRecipe))){
                result.add(recipeEntity);
            }
        }
        return result;
    }

    @Override
    public ArrayList<RecipeEntity> fetchActiveUserRecipe(Integer idUser) {
        Iterable<RecipeEntity> listOfRecipeEntitie = recipeService.findAll();
        ArrayList<RecipeEntity> result = new ArrayList<>();
        for(RecipeEntity recipeEntity:listOfRecipeEntitie){
            if((recipeEntity.getUserId().equals(idUser))&&
                    (recipeEntity.getRecipeStatement())){
                result.add(recipeEntity);
            }
        }
        return result;
    }


    @Override
    public ArrayList<Integer> fetchAllUserRecipe(Integer idUser) {
        ArrayList<Integer> result = new ArrayList<>();
        Iterable<RecipeEntity> listOfRecipeEntitie = recipeService.findAll();
        for(RecipeEntity recipeEntity:listOfRecipeEntitie){
            if((recipeEntity.getUserId().equals(idUser))&&
                    (!isListContain(result,recipeEntity.getRecipeId()))){
                result.add(recipeEntity.getRecipeId());
            }
        }
        return result;
    }

    @Override
    public void createRecipe(ArrayList<RecipeEntity> listOfRecipeEntity) {
        RecipeEntity recipeEntity = recipeService.save(listOfRecipeEntity.get(0));
        listOfRecipeEntity.remove(0);
        recipeEntity.setRecipeId(recipeEntity.getId());
        recipeService.save(recipeEntity);
        if(!listOfRecipeEntity.isEmpty()) {
            recipeService.save(listOfRecipeEntity);
        }
    }

    @Override
    public void deleteRecipe(Integer idRecipe, Integer idUser) {
        Iterable<RecipeEntity> listOfRecipeEntity = recipeService.findAll();
        for(RecipeEntity recipeEntity:listOfRecipeEntity){
            if((recipeEntity.getUserId().equals(idUser))&&
                    (recipeEntity.getRecipeId().equals(idRecipe))){
                recipeService.delete(recipeEntity.getId());
            }
        }
    }

    @Override
    public void addProductInRecipe(RecipeEntity recipeEntity) {
        recipeService.save(recipeEntity);
    }

    @Override
    public void removeProductFromUserRecipe(Integer idUser,Integer idRecipe, Integer idProduct) {
        Iterable<RecipeEntity> listOfRecipeEntitie = recipeService.findAll();
        for(RecipeEntity recipeEntity:listOfRecipeEntitie){
            if((recipeEntity.getUserId().equals(idUser))&&
                    (recipeEntity.getRecipeId().equals(idRecipe))&&
                    (recipeEntity.getProductId().equals(idProduct))){
                recipeService.delete(recipeEntity.getId());
                return;
            }
        }
    }

    private boolean isListContain(ArrayList<Integer> list,Integer checkInteger){
        for(Integer integer : list){
            if(integer.equals(checkInteger)) return true;
        }
        return false;
    }
}
