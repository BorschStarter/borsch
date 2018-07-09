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
        foodCache.put("1", new Food("1","Apple","1"));
        foodCache.put("2", new Food("2","Orange","1"));
        foodCache.put("3", new Food("3","Potato","1"));
        foodCache.put("4", new Food("4","Tomato","1"));
        foodCache.put("5", new Food("5","Meat","1"));
        foodCache.put("6", new Food("6","Chicken","1"));
        foodCache.put("7", new Food("7","Dog","1"));
        foodCache.put("8", new Food("8","Cat","1"));
        foodCache.put("9", new Food("9","Fish","1"));
        foodCache.put("10", new Food("10","Apple","1"));
        foodCache.put("11", new Food("11","Orange","1"));
        foodCache.put("12", new Food("12","Potato","1"));
        foodCache.put("13", new Food("13","Tomato","1"));
        foodCache.put("14", new Food("14","Meat","1"));
        foodCache.put("15", new Food("15","Chicken","1"));
        foodCache.put("16", new Food("16","Dog","1"));
        foodCache.put("17", new Food("17","Cat","1"));
        foodCache.put("18", new Food("18","Fish","1"));
        foodCache.put("19", new Food("19","Apple","1"));
        foodCache.put("20", new Food("20","Orange","1"));
        foodCache.put("21", new Food("21","Potato","1"));
        foodCache.put("22", new Food("22","Tomato","1"));
        foodCache.put("23", new Food("23","Meat","1"));
        foodCache.put("24", new Food("24","Chicken","1"));
        foodCache.put("25", new Food("25","Dog","1"));
        foodCache.put("26", new Food("26","Cat","1"));
        foodCache.put("27", new Food("27","Fish","1"));
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
