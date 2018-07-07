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

        if (id == null){
            throw new IllegalArgumentException("Вы ввели null");
        }

        return userRepository.fetchUser(id).getFridge();
    }

    @Override
    public Fridge addProductInFridge(String id, Product product){

        if (id == null || product == null){
            throw new IllegalArgumentException("Вы ввели null");
        }

        addProductToFridge(userRepository.fetchUser(id), product);
        return provideUserFridge(id);
    }

    @Override
    //получить список food, которая начинается на foodname
    //засунуть в FoodService
    public List<Food> getFoodSearchList(String foodName){

        if (foodName == null){
            throw new IllegalArgumentException("Вы ввели null");
        }

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




    private void addProductToFridge(User user, Product product){

        if (user == null || product == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else {
            user.getFridge().getProducts().put(product.getId(),product);
        }
    }

    public void removeProductFromFridge(User user, Product product){

        if (user == null || product == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!user.getFridge().getProducts().containsKey(product.getId())){
            throw new IllegalArgumentException("Такого продукта нет в холодильнике данного пользователя");
        }
        else {
            user.getFridge().getProducts().remove(product.getId());
        }
    }

    public Product getProductFromFridge(User user, Product product){
        if (user == null || product == null){
            throw new IllegalArgumentException("Вы ввели null");
        }
        else if (!user.getFridge().getProducts().containsKey(product.getId())){
            throw new IllegalArgumentException("Такого продукта нет в холодильнике данного пользователя");
        }
        else {
            return user.getFridge().getProducts().get(product.getId());
        }
    }
}
