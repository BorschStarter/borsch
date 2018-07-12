package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.models.Product;

public interface ProductRepository {

    ProductEntity fetchProduct(Integer productId);

    ProductEntity createProduct(ProductEntity productEntity);

    void removeProduct(Integer productId);
}
