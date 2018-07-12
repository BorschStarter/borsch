package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.entity.RecipeCookerEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecipeCookerRepositoryEntity extends CrudRepository<RecipeCookerEntity, Integer> {
}
