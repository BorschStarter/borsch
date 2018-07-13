package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.UserForEachProductListEntity;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.UsersForEachProductRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.UserForEachProductRepositoryEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class PostgresUsersForEachProductRepository implements UsersForEachProductRepository {

    @Autowired
    private UserForEachProductRepositoryEntity service;

    @Override
    public UserForEachProductListEntity createUserForEachProductList(UserForEachProductListEntity userForEachProductListEntity) {
        service.save(userForEachProductListEntity);
        return userForEachProductListEntity;
    }

    @Override
    public ArrayList<UserForEachProductListEntity> fetchUserForEachProductList(Integer recipeId, Integer productId) {
        ArrayList<UserForEachProductListEntity> result = new ArrayList<>();

        Iterable<UserForEachProductListEntity> tmp = service.findAll();
        tmp.forEach(item -> {
            if(item.getRecipeId().equals(recipeId) && item.getProductId().equals(productId)){
                result.add(item);
            }
        });
        return result;
    }

    @Override
    public void deleteUserForEachProductList(Integer id) {
        service.delete(id);
    }

    @Override
    public void removeAllUserForEachProductList(Integer recipeId, Integer productId) {
        ArrayList<UserForEachProductListEntity> result = new ArrayList<>();

        Iterable<UserForEachProductListEntity> tmp = service.findAll();
        tmp.forEach(item -> {
            if(item.getRecipeId().equals(recipeId) && item.getProductId().equals(productId)){
                service.delete(item);
            }
        });
    }
}
