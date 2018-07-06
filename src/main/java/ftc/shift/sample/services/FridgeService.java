package ftc.shift.sample.services;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FridgeService implements FridgeServiceInterface {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public FridgeService(final UserRepository userRepository, FoodRepository foodRepository) {
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public Fridge provideUserFridge(String id){
        return userRepository.fetchUser(id).getFridge();
    }

    @Override
    public Fridge addProductInFridge(String id, Product product){
        addProductToFridge(userRepository.fetchUser(id), product);
        return provideUserFridge(id);
    }

    @Override
    public List<Food> getFoodSearchList(String foodName){

        List<Food> list = new ArrayList<>();
        int length = foodName.length();

        for (String key : foodRepository.getAllFoods().keySet()) {
            if (key.length() >= length && key.startsWith(foodName)){
                list.add(foodRepository.fetchFood(key));
            }
        }
        if (list.isEmpty())
            throw new IllegalArgumentException("Продуктов с таким именем нет в базе");
        else return list;
    }



    //Сверху методы для интерфейса, снизу внутренние методы
    //Добавь все желаемые методы в FridgeServiceInterface




    public void addProductToFridge(User user, Product product){

        if (user == null || product == null){
            throw new IllegalArgumentException();
        }
        else {
            user.getFridge().getProducts().put(product.getId(),product);
        }
    }

    public void removeProductFromFridge(User user, Product product){

        if (user == null || product == null || !user.getFridge().getProducts().containsKey(product.getId())){
            throw new IllegalArgumentException();
        }
        else {
            user.getFridge().getProducts().remove(product.getId());
        }
    }

    public Product getProductFromFridge(User user, Product product){
        if (user == null || product == null || !user.getFridge().getProducts().containsKey(product.getId())){
            throw new IllegalArgumentException();
        }
        else {
            return user.getFridge().getProducts().get(product.getId());
        }
    }
}
