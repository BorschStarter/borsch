package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.FoodEntity;
import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.FoodRepositoryEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFoodRepository implements FoodRepository {

    private TreeMap<String, Food> foodCache = new TreeMap<>();

    @Autowired
    private FoodRepositoryEntity service;

    public InMemoryFoodRepository(){}

    @Override
    public Food fetchFood(@NonNull final Integer idFood) {
        FoodEntity foodEntity = service.findOne(idFood);
        return foodEntity.toFood();
    }

    @Override
    public Food updateFood(@NonNull final Food food) {
        service.delete(food.getId());
        service.save(food.toFoodEntity());
        return food;
    }

    @Override
    public void deleteFood(@NonNull final Integer idFood) {
        service.delete(idFood);
    }

    @Override
    public Food createFood(@NonNull final Food food) {
        //food.setId(null);
        //service.save(food.toFoodEntity());
        //Метод требует подключения дополнительного сервиса по составлению SQL запросов
        //Жаль Малой кровью не обошлись
        return service.save(food.toFoodEntity()).toFood();
    }

    @Override
    public TreeMap<String,Food> getAllFoods() {
//        TreeMap<String,Food> map = new TreeMap<>();
//        Iterable<FoodEntity> list = service.findAll();
//        for(FoodEntity foodEntity : list){
//            map.put(foodEntity.getName(),foodEntity.toFood());
//        }
//        return map;
        Iterable<FoodEntity> foods = service.findAll();
        TreeMap<String,Food> listFood = new TreeMap<>();
        foods.forEach(item -> listFood.put(item.getId().toString(),item.toFood()));

        return listFood;
    }
}
