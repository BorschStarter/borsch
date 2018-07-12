package ftc.shift.sample.services;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FridgeService implements FridgeServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public FridgeService(final UserRepository userRepository, FoodRepository foodRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Fridge provideFridge(@NonNull Integer idUser){

        return userRepository.fetchUser(idUser).getFridge();
    }


    @Override
    public Fridge addProductInFridge(@NonNull Integer idUser,@NonNull Product product){

        userRepository.fetchUser(idUser).getFridge().getProducts().put(product.getFoodName(),product);

        return provideFridge(idUser);
    }

    @Override
    public Fridge removeProductFromFridge(@NonNull Integer idUser,@NonNull Integer idProduct) {
        if (!provideFridge(idUser).getProducts().containsKey(idProduct)){
            throw new IllegalArgumentException("Такого продукта нет в холодильнике данного пользователя");
        }
        else {
            provideFridge(idUser).getProducts().remove(idProduct);
            return provideFridge(idUser);
        }
    }

    @Override
    public Product getProductFromFridge(@NonNull Integer idUser,@NonNull Integer idProduct){

        if (!provideFridge(idUser).getProducts().containsKey(idProduct)){
            throw new IllegalArgumentException("Такого продукта нет в холодильнике данного пользователя");
        }
        else {
            return provideFridge(idUser).getProducts().get(idProduct);
        }
    }
}