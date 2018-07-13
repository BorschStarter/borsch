package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.RecipeActivityEntity;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.RecipeActivityRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.RecipeActivityRepositoryEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class PostgresRecipeActivityRepository implements RecipeActivityRepository{

    @Autowired
    private RecipeActivityRepositoryEntity recipeActivityService;

    @Override
    public RecipeActivityEntity createRecipeActivityEntity(RecipeActivityEntity recipeActivityEntity) {
        recipeActivityService.save(recipeActivityEntity);
        return recipeActivityEntity;
    }

    @Override
    public RecipeActivityEntity updateRecipeActivityEntity(RecipeActivityEntity recipeActivityEntity) {
        recipeActivityService.delete(recipeActivityEntity.getId());
        recipeActivityService.save(recipeActivityEntity);
        return recipeActivityService.findOne(recipeActivityEntity.getId());
    }

    @Override
    public ArrayList<RecipeActivityEntity> fetchRecipeActivityEntity(Integer userId) {
        ArrayList<RecipeActivityEntity> result = new ArrayList<>();

        Iterable<RecipeActivityEntity> tmp = recipeActivityService.findAll();
        tmp.forEach(item -> {
            if(item.getUserId().equals(userId)){
                result.add(item);
            }
        });
        return result;
    }

    @Override
    public void removeRecipeActivityEntity(Integer userId, Integer recipeId, Integer productId) {
        Iterable<RecipeActivityEntity> tmp = recipeActivityService.findAll();
        tmp.forEach(item -> {
            if(item.getUserId().equals(userId) && item.getRecipeId().equals(recipeId) && item.getProductId().equals(productId)){
                recipeActivityService.delete(item);
            }
        });
    }
}
