package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.FoodEntity;
import ftc.shift.sample.models.Food;
import lombok.NonNull;

import java.util.List;
import java.util.TreeMap;

public interface FoodRepository {

    FoodEntity fetchFood(Integer idFood);

    FoodEntity updateFood(FoodEntity foodEntity);

    void deleteFood(Integer idFood);

    FoodEntity createFood(FoodEntity foodEntity);

    TreeMap<String,Food> getAllFoods();
}
