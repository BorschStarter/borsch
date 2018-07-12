package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import lombok.NonNull;

public interface FridgeServiceInterface {

    Fridge provideFridge(@NonNull Integer idUser);

    Fridge addProductInFridge(@NonNull Integer idUser,@NonNull  Product product);

    //IllegalArgumentException("Такого продукта нет в холодильнике данного пользователя")
    Fridge removeProductFromFridge(@NonNull Integer idUser,@NonNull  Integer idProduct);

    //IllegalArgumentException("Такого продукта нет в холодильнике данного пользователя")
    Product getProductFromFridge(@NonNull Integer idUser,@NonNull Integer idProduct);
}