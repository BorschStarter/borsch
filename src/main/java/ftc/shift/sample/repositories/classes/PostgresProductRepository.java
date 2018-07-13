package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.ProductRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.ProductRepositoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresProductRepository implements ProductRepository {

    @Autowired
    private ProductRepositoryEntity productService;

    @Override
    public ProductEntity fetchProduct(Integer productId) {
        return productService.findOne(productId);
    }

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        ProductEntity result = productService.save(productEntity);
        return result;
    }

    @Override
    public void removeProduct(Integer productId) {
        productService.delete(productId);
    }
}
