package ftc.shift.sample.services;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(final FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food provideFood(String id) {

        if (foodRepository.getAllFoods().containsKey(id))
            return foodRepository.fetchFood(id);
        else throw new IllegalArgumentException();
    }

    public Food updateFood(Food food) {

        if (food == null)
            throw new IllegalArgumentException();
        else foodRepository.updateFood(food);
        return food;
    }

    public void deleteFood(String id) {
        if (foodRepository.getAllFoods().containsKey(id))
            foodRepository.deleteFood(id);
        else throw new IllegalArgumentException();
    }

    public Food createFood(Food food) {
        if (food == null)
            throw new IllegalArgumentException();
        else foodRepository.createFood(food);
        return food;
    }

    public TreeMap<String,Food> provideFoods() {
        return foodRepository.getAllFoods();
    }

    //public Collection<Food> (string)  вернуть список фудв начинающихся на string
}