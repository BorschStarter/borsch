package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.ProductRepository;

public class PostgresProductRepository implements ProductRepository {
    @Override
    public ProductEntity fetchProduct(Integer productId) {
        return null;
    }

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        return null;
    }

    @Override
    public void removeProduct(Integer productId) {

    }
}
