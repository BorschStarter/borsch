package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFoodRepository implements FoodRepository {

    private TreeMap<String, Food> foodCache = new TreeMap<>();

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
