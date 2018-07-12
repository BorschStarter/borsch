package ftc.shift.sample.services;

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
    public Fridge provideFridge(@NonNull Integer idUser) {

        return null;
    }


    @Override
    public Fridge addProductInFridge(@NonNull Integer idUser, @NonNull Product product) {
//        User user = userRepository.fetchUser(idUser);
//        user.getFridge().getProducts().put(product.getFoodName(),product);
//        userRepository.updateUser(user);
//        return provideFridge(idUser);
        return null;
    }

    @Override
    public Fridge removeProductFromFridge(@NonNull Integer idUser, @NonNull Integer idProduct) {
//        if (!provideFridge(idUser).getProducts().containsKey(idProduct)){
//            throw new IllegalArgumentException("Такого продукта нет в холодильнике данного пользователя");
//        }
//        else {
//            User user = userRepository.fetchUser(idUser);
//            user.getFridge().getProducts().remove(idProduct);
//            userRepository.updateUser(user);
//            return provideFridge(idUser);
//        }
        return null;
    }

    @Override
    public Product getProductFromFridge(@NonNull Integer idUser, @NonNull Integer idProduct) {
//
//        if (!provideFridge(idUser).getProducts().containsKey(idProduct)){
//            throw new IllegalArgumentException("Такого продукта нет в холодильнике данного пользователя");
//        }
//        else {
//            return provideFridge(idUser).getProducts().get(idProduct);
//        }
//    }
        return null;
    }
}