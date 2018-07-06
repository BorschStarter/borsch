package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.Food;

import java.util.TreeMap;

public interface FoodRepository {

    Food fetchFood(String id);

    Food updateFood(Food book);

    void deleteFood(String id);

    Food createFood(Food book);

    TreeMap<String,Food> getAllFoods();
}
