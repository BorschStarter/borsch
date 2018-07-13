package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.UserForEachProductListEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserForEachProductRepositoryEntity extends CrudRepository<UserForEachProductListEntity, Integer> {}
