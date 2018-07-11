package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.Food;
import lombok.NonNull;

import java.util.List;
import java.util.TreeMap;

public interface FoodRepository {

    Food fetchFood(@NonNull Integer idFood);

    Food updateFood(@NonNull Food food);

    void deleteFood(@NonNull Integer idFood);

    Food createFood(@NonNull Food food);

    TreeMap<String,Food> getAllFoods();
}
