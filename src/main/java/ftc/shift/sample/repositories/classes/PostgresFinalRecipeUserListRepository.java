package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.FinalRecipeUserListEntity;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FinalRecipeUserListRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.FinalRecipeUserListRepositoryEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class PostgresFinalRecipeUserListRepository implements FinalRecipeUserListRepository {

    @Autowired
    private FinalRecipeUserListRepositoryEntity service;

    @Override
    public FinalRecipeUserListEntity createFinalRecipeUserListEntity(FinalRecipeUserListEntity finalRecipeUserListEntity) {
        service.save(finalRecipeUserListEntity);
        return finalRecipeUserListEntity;
    }

    @Override
    public ArrayList<FinalRecipeUserListEntity> fetchFinalRecipeUserList(Integer recipeId) {
        ArrayList<FinalRecipeUserListEntity> result = new ArrayList<>();

        Iterable<FinalRecipeUserListEntity> tmp = service.findAll();
        tmp.forEach(item -> {
            if(item.getRecipeId().equals(recipeId)){
                result.add(item);
            }
        });
        return result;
    }

    @Override
    public void removeFinalRecipeUserList(Integer id) {
        service.delete(id);
    }

    @Override
    public void removeAllFinalRecipeUserList(Integer recipeId) {
        Iterable<FinalRecipeUserListEntity> tmp = service.findAll();
        tmp.forEach(item -> {
            if(item.getRecipeId().equals(recipeId)){
                service.delete(item.getId());
            }
        });
    }
}
