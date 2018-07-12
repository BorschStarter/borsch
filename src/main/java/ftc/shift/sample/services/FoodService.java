package ftc.shift.sample.services;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.entity.FoodEntity;
import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FoodRepository;
import ftc.shift.sample.services.Interfaces.FoodServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.TreeMap;

@Service
public class FoodService implements FoodServiceInterface {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(final FoodRepository foodRepository) {

        this.foodRepository = foodRepository;
    }

    private static final String CANT_FIND_ANY_FOOD = "Не найдено ни одногоо проодукта";
    private static final String CANT_FIND_FOOD = "Продукт не найден в базе";
    private static final String FOOD_BASE_IS_EMPTY = "База продуктов пуста";


    @Override
    public ArrayList<Food> getListFoodStartWith(@NonNull String startNameOfFood) throws IllegalArgumentException{

        ArrayList<Food> list = new ArrayList<>();
        startNameOfFood = startNameOfFood.toLowerCase(Locale.ENGLISH);
        TreeMap<String,Food> map = foodRepository.getAllFoods();

        for (String idFood : map.keySet()) {
            if ( idFood.toLowerCase(Locale.ENGLISH).startsWith(startNameOfFood)){
                Food food = EntityProcessor.foodEntityToFood(
                        foodRepository.fetchFood(map.get(idFood).getId()));
                list.add(food);
            }
        }

        if (list.isEmpty())
            throw new IllegalArgumentException(CANT_FIND_ANY_FOOD);
        else return list;

    }

    @Override
    public Food provideFood(@NonNull Integer idFood) throws IllegalArgumentException{
        FoodEntity foodEntity = foodRepository.fetchFood(idFood);
        if (foodEntity!=null)
            return EntityProcessor.foodEntityToFood(foodEntity);
        else throw new IllegalArgumentException(CANT_FIND_FOOD);
    }

    @Override
    public Food updateFood(@NonNull Food food) throws IllegalArgumentException {
        try{
            provideFood(food.getId());
            FoodEntity foodEntity = EntityProcessor.foodToFoodEntity(food);
            foodEntity = foodRepository.updateFood(foodEntity);
            food=EntityProcessor.foodEntityToFood(foodEntity);
            return food;
        }catch (IllegalArgumentException ex){
            throw ex;
        }

    }

    @Override
    public void deleteFood(@NonNull Integer idFood) throws IllegalArgumentException {
        FoodEntity foodEntity = foodRepository.fetchFood(idFood);
        if (foodEntity!=null)
            foodRepository.deleteFood(idFood);
        else throw new IllegalArgumentException(CANT_FIND_FOOD);
    }

    @Override
    public Food createFood(@NonNull Food food) {
        FoodEntity foodEntity = EntityProcessor.foodToFoodEntity(food);
        foodEntity = foodRepository.createFood(foodEntity);
        food = EntityProcessor.foodEntityToFood(foodEntity);
        return food;
    }

    @Override
    public TreeMap<String,Food> provideFoods() throws NoSuchElementException {
        TreeMap<String,Food> map =foodRepository.getAllFoods();
        if (map.size()==0){
            throw new NoSuchElementException(FOOD_BASE_IS_EMPTY);
        }
        return foodRepository.getAllFoods();
    }
}