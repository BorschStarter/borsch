package ftc.shift.sample.services;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.entity.FridgeEntity;
import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FridgeRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.ProductRepository;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static ftc.shift.sample.services.ServiceResources.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class FridgeService implements FridgeServiceInterface {

    private final FridgeRepository fridgeRepository;
    private final ProductRepository productRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public FridgeService(FridgeRepository fridgeRepository,
                         ProductRepository productRepository,
                         FoodRepository foodRepository) {
        this.fridgeRepository = fridgeRepository;
        this.productRepository = productRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public Fridge provideFridge(@NonNull Integer idUser) throws NoSuchElementException {
        Fridge fridge = new Fridge();
        ArrayList<FridgeEntity> fridgeList = fridgeRepository.fetchUserFridge(idUser);
        if(fridgeList.isEmpty()){
            throw new NoSuchElementException(USERS_FRIDGE_IS_EMPTY);
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
            throw new IllegalArgumentException(INCORRECT_PRODUCT_FORM );
        }else {
            ProductEntity productEntity = EntityProcessor.productToProductEntity(product);
            if (isProductAlreadyInFridge(productEntity.getFoodName(), idUser)) {
                throw new IllegalArgumentException(PRODUCT_ALREADY_IN_USER_FRIDGE);
            } else{
                FridgeEntity fridgeEntity = new FridgeEntity();
                fridgeEntity.setUserId(idUser);
                productEntity = productRepository.createProduct(productEntity);
                fridgeEntity.setProductId(productEntity.getId());
                fridgeRepository.addProductInUserFridge(fridgeEntity);
            }
        }
    }

    @Override
    public void removeProductFromFridge(@NonNull Integer idUser, @NonNull Integer idProduct) {
        if(isProductInFridge(idProduct,idUser)){
            throw new IllegalArgumentException(NO_THAT_PRODUCT_IN_USER_FRIDGE);
        }else{
            fridgeRepository.removeProductFromUserFridge(idUser,idProduct);
            productRepository.removeProduct(idProduct);
        }

    }

    @Override
    public Product getProductFromFridge(@NonNull Integer idUser, @NonNull Integer idProduct) {
        ArrayList<FridgeEntity> fridgeList = fridgeRepository.fetchUserFridge(idUser);
        if(fridgeList.isEmpty()){
            throw new NoSuchElementException(USERS_FRIDGE_IS_EMPTY);
        }else{
            for(FridgeEntity fridgeEntity : fridgeList){
                ProductEntity productEntity = productRepository
                        .fetchProduct(fridgeEntity.getProductId());
                if(productEntity.getId()==idProduct){
                    return EntityProcessor.productEntityToProduct(productEntity);
                }
            }
        }
        throw new NoSuchElementException(NO_THAT_PRODUCT_IN_USER_FRIDGE);
    }

    private Boolean isProductCorrect(Product product){
        if(product.getAllWeight()==null)return false;
        if(product.getAllWeight()<1) return false;
        if(product.getFoodId()==null) return false;
        if(foodRepository.fetchFood(product.getFoodId())==null) return false;
        if(product.getReservedWeight()==null) return false;
        if(product.getReservedWeight()!=0) return false;
        return true;
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