package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFoodRepository implements FoodRepository {

    private TreeMap<String, Food> foodCache = new TreeMap<>();

    public InMemoryFoodRepository(){
        foodCache.put("1", new Food("1","яблоко","1"));
        foodCache.put("2", new Food("2","апельсин","1"));
        foodCache.put("3", new Food("3","картофель","1"));
        foodCache.put("4", new Food("4","томат","1"));
        foodCache.put("5", new Food("5","говядина","1"));
        foodCache.put("6", new Food("6","курица","1"));
        foodCache.put("7", new Food("7","свинина","1"));
        foodCache.put("8", new Food("8","мука","1"));
        foodCache.put("9", new Food("9","лосось","1"));
        foodCache.put("10", new Food("10","масло","1"));
        foodCache.put("11", new Food("11","молоко","1"));
        foodCache.put("12", new Food("12","вода","1"));
        foodCache.put("13", new Food("13","сахар","1"));
        foodCache.put("14", new Food("14","соль","1"));
        foodCache.put("15", new Food("15","яйцо","1"));
        foodCache.put("16", new Food("16","банан","1"));
        foodCache.put("17", new Food("17","хлеб","1"));
        foodCache.put("18", new Food("18","вишня","1"));
        foodCache.put("19", new Food("19","капуста","1"));
        foodCache.put("20", new Food("20","перец","1"));
        foodCache.put("21", new Food("21","морковь","1"));
        foodCache.put("22", new Food("22","лук","1"));
        foodCache.put("23", new Food("23","чеснок","1"));
        foodCache.put("24", new Food("24","лавровый лист","1"));
        foodCache.put("25", new Food("25","шампиньоны","1"));
        foodCache.put("26", new Food("26","укроп","1"));
        foodCache.put("27", new Food("27","петрушка","1"));
        foodCache.put("28", new Food("28","сыр","1"));
        foodCache.put("29", new Food("29","пиво","1"));
        foodCache.put("30", new Food("30","вино","1"));
    }

    @Override
    public Food fetchFood(@NonNull final String idFood) {
        return foodCache.get(idFood);
    }

    @Override
    public Food updateFood(@NonNull final Food food) {
        foodCache.put(food.getId(), food);
        return food;
    }

    @Override
    public void deleteFood(@NonNull final String idFood) {
        foodCache.remove(idFood);
    }

    @Override
    public Food createFood(@NonNull final Food food) {
        food.setId(food.getName());
        foodCache.put(food.getName(), food);
        return food;
    }

    @Override
    public TreeMap<String,Food> getAllFoods() {
        return foodCache;
    }
}
