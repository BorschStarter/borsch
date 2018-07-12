package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.LoginEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepositoryEntity extends CrudRepository<LoginEntity, Integer> {
}
