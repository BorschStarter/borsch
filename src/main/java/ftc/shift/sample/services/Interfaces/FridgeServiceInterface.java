package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import lombok.NonNull;

public interface FridgeServiceInterface {

    Fridge provideFridge(@NonNull Integer idUser);

    void addProductInFridge(@NonNull Integer idUser,@NonNull  Product product);

    void removeProductFromFridge(@NonNull Integer idUser,@NonNull  Integer idProduct);

    Product getProductFromFridge(@NonNull Integer idUser,@NonNull Integer idProduct);
}