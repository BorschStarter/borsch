package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.UserForEachProductListEntity;

import java.util.ArrayList;

public interface UsersForEachProductRepository {
    UserForEachProductListEntity createUserForEachProductList(UserForEachProductListEntity userForEachProductListEntity);

    ArrayList<UserForEachProductListEntity> fetchUserForEachProductList(Integer recipeId, Integer productId);

    void deleteUserForEachProductList(Integer id);

    void removeAllUserForEachProductList(Integer recipeId, Integer productId);
}
