package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.FridgeEntity;
import ftc.shift.sample.entity.ProductEntity;
import lombok.NonNull;

import java.util.ArrayList;

public interface FridgeRepository  {

    ArrayList<FridgeEntity> fetchUserFridge(Integer idUser);
    
    void addProductInUserFridge(Integer idUser, ProductEntity productEntity);
    
    void removeProductFromUserFridge(Integer idUser,Integer idProduct);
    
    ProductEntity getProductFromUserFridge( Integer idUser,  Integer idProduct);
}
