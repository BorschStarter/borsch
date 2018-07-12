package ftc.shift.sample.repositories.interfaces.EntityUnterfaces;

import ftc.shift.sample.entity.FridgeEntity;
import ftc.shift.sample.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositoryEntity extends CrudRepository<ProductEntity, Integer> {
}
