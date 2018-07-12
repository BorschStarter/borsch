package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.FridgeEntity;
import org.springframework.data.repository.CrudRepository;

public interface FridgeRepositoryEntity extends CrudRepository<FridgeEntity, Integer> {
}
