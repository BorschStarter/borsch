package ftc.shift.sample.repositories.classes;


import ftc.shift.sample.entity.FridgeEntity;
import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FridgeRepository;

import java.util.ArrayList;

public class PostgresFridgeRepository  implements FridgeRepository {

    @Override
    public ArrayList<FridgeEntity> fetchUserFridge(Integer idUser) {
        return null;
    }

    @Override
    public void addProductInUserFridge(Integer idUser, ProductEntity productEntity) {

    }

    @Override
    public void removeProductFromUserFridge(Integer idUser, Integer idProduct) {

    }

    @Override
    public ProductEntity getProductFromUserFridge(Integer idUser, Integer idProduct) {
        return null;
    }
}
