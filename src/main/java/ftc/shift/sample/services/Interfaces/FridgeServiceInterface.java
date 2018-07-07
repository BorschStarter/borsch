
package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;


import java.util.List;

public interface FridgeServiceInterface {

    //IllegalArgumentException("Вы ввели null");
    Fridge provideUserFridge(String id);

    //IllegalArgumentException("Вы ввели null");
    Fridge addProductInFridge(String id, Product product);

    //IllegalArgumentException("Вы ввели null");
    //IllegalArgumentException("Продуктов с таким именем нет в базе");
    List<Food> getFoodSearchList(String foodName);
}