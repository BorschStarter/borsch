package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFoodRepository implements FoodRepository {

    private Map<String, Food> foodCache = new HashMap<>();

    public InMemoryFoodRepository() {}


    @Override
    public Food fetchFood(final String id) {
        return foodCache.get(id);
    }

    @Override
    public Food updateFood(final Food food) {
        foodCache.put(food.getId(), food);
        return food;
    }

    @Override
    public void deleteFood(final String id) {
        foodCache.remove(id);
    }

    @Override
    public Food createFood(final Food food) {
        food.setId(String.valueOf(System.currentTimeMillis()));
        foodCache.put(food.getId(), food);
        return food;
    }

    @Override
    public Collection<Food> getAllFoods() {
        return foodCache.values();
    }
}
