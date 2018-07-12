package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.FoodEntity;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepositoryEntity extends CrudRepository<FoodEntity, Integer> {
}
