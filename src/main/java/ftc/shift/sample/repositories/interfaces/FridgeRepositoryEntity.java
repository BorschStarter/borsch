package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.entity.FridgeEntity;
import ftc.shift.sample.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface FridgeRepositoryEntity extends CrudRepository<FridgeEntity, Integer> {
}
