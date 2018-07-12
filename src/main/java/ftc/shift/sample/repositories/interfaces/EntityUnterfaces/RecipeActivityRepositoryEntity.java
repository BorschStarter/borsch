package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.entity.RecipeActivityEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecipeActivityRepositoryEntity extends CrudRepository<RecipeActivityEntity, Integer> {
}
