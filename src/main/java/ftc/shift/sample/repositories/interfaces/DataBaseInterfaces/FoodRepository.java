package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.entity.FoodEntity;
import ftc.shift.sample.models.Food;
import lombok.NonNull;

import java.util.List;
import java.util.TreeMap;

public interface FoodRepository {

    FoodEntity fetchFood(@NonNull Integer idFood);

    FoodEntity updateFood(@NonNull FoodEntity foodEntity);

    void deleteFood(@NonNull Integer idFood);

    FoodEntity createFood(@NonNull FoodEntity foodEntity);

    TreeMap<String,Food> getAllFoods();
}
