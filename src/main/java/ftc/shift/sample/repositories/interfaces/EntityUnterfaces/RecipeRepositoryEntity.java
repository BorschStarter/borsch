package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.FoodEntity;
import ftc.shift.sample.entity.RecipeEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepositoryEntity extends CrudRepository<RecipeEntity, Integer> {
}
