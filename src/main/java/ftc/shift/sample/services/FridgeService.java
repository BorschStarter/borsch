package ftc.shift.sample.services;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.entity.FridgeEntity;
import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FridgeRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.ProductRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class FridgeService implements FridgeServiceInterface {

    private final FridgeRepository fridgeRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FridgeService(FridgeRepository fridgeRepository, ProductRepository productRepository) {
        this.fridgeRepository = fridgeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Fridge provideFridge(@NonNull Integer idUser) throws NoSuchElementException {
        Fridge fridge = new Fridge();
        ArrayList<FridgeEntity> fridgeList = fridgeRepository.fetchUserFridge(idUser);
        if(fridgeList.isEmpty()){
            throw new NoSuchElementException("Холодильник пользователя пуст");
        }else{
            for(FridgeEntity fridgeEntity : fridgeList){
                Product product = EntityProcessor.productEntityToProduct(
                        productRepository.fetchProduct(fridgeEntity.getProductId()));
                fridge.getProducts().add(product);
            }
        }
        return fridge;
    }


    @Override
    public void addProductInFridge(@NonNull Integer idUser, @NonNull Product product) throws IllegalArgumentException {
        if(!isProductCorrect(product)){
            throw new IllegalArgumentException("Передана некорректная форма проодукта");
        }else {
            ProductEntity productEntity = EntityProcessor.productToProductEntity(product);
            if (isProductAlreadyInFridge(productEntity.getFoodName(), idUser)) {
                throw new IllegalArgumentException("Продукт уже в холодильнике пользователя");
            } else{
                FridgeEntity fridgeEntity = new FridgeEntity();
            fridgeEntity.setUserId(idUser);
            productEntity = productRepository.createProduct(productEntity);
            fridgeEntity.setProductId(productEntity.getId());
            }
        }
    }

    @Override
    public void removeProductFromFridge(@NonNull Integer idUser, @NonNull Integer idProduct) {
        if(isProductInFridge(idProduct,idUser)){
            throw new IllegalArgumentException("В холодильнике пользователя нет такого продукта");
        }else{
            fridgeRepository.removeProductFromUserFridge(idUser,idProduct);
            productRepository.removeProduct(idProduct);
        }

    }

    @Override
    public Product getProductFromFridge(@NonNull Integer idUser, @NonNull Integer idProduct) {
        ArrayList<FridgeEntity> fridgeList = fridgeRepository.fetchUserFridge(idUser);
        if(fridgeList.isEmpty()){
            throw new NoSuchElementException("Холодильник пользователя пуст");
        }else{
            for(FridgeEntity fridgeEntity : fridgeList){
                ProductEntity productEntity = productRepository
                        .fetchProduct(fridgeEntity.getProductId());
                if(productEntity.getId()==idProduct){
                    return EntityProcessor.productEntityToProduct(productEntity);
                }
            }
        }
        throw new NoSuchElementException("В холодильнике пользователя нет такого продукта");
    }

    private Boolean isProductCorrect(Product product){

        return null;
    }

    private Boolean isProductAlreadyInFridge(String productName, Integer idUser){
        ArrayList<FridgeEntity> fridgeList = fridgeRepository.fetchUserFridge(idUser);
        for(FridgeEntity fridgeEntity : fridgeList) {
            ProductEntity productEntity = productRepository
                    .fetchProduct(fridgeEntity.getProductId());
            if(productEntity.getFoodName().equals(productName)){
                return true;
            }
        }
        return false;
    }

    private Boolean isProductInFridge(Integer productId, Integer idUser){

        ProductEntity productEntity = productRepository.fetchProduct(productId);
        if(productEntity==null){
            return true;
        }
        return false;
    }
}