package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.UserValidInfo;

import java.util.List;

public interface FridgeService {
    Fridge provideUserFridge(UserValidInfo userValidInfo);

    Fridge addProductInFridge(UserValidInfo userValidInfo, Product product);

    List<Product> getProductSearchList(String foodName);


}
