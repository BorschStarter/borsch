package ftc.shift.sample.services;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(final FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food provideFood(String id) {
        return foodRepository.fetchFood(id);
    }

    public Food updateFood(Food food) {
        foodRepository.updateFood(food);
        return food;
    }

    public void deleteFood(String id) {
        foodRepository.deleteFood(id);
    }


    public Food createFood(Food food) {
        foodRepository.createFood(food);
        return food;
    }

    public Collection<Food> provideFoods() {
        return foodRepository.getAllFoods();
    }

}