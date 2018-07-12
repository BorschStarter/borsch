package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.UserInfoEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryEntity extends CrudRepository<UserInfoEntity, Integer> {
}
