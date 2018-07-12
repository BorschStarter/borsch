package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.entity.FoodEntity;
import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.FoodRepositoryEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFoodRepository implements FoodRepository {

    private final FoodRepositoryEntity service;

    @Autowired
    public InMemoryFoodRepository(FoodRepositoryEntity service){
        this.service = service;
    }

    @Override
    public Food fetchFood(@NonNull final Integer idFood) {
        FoodEntity foodEntity = service.findOne(idFood);
        return EntityProcessor.foodEntityToFood(foodEntity);
    }

    @Override
    public Food updateFood(@NonNull final Food food) {
        service.delete(food.getId());
        service.save(EntityProcessor.foodToFoodEntity(food));
        return food;
    }

    @Override
    public void deleteFood(@NonNull final Integer idFood) {
        service.delete(idFood);
    }

    @Override
    public Food createFood(@NonNull final Food food) {
        return EntityProcessor.foodEntityToFood(service.save(EntityProcessor.foodToFoodEntity(food)));
    }

    @Override
    public TreeMap<String,Food> getAllFoods() {
        Iterable<FoodEntity> foods = service.findAll();
        TreeMap<String,Food> listFood = new TreeMap<>();
        foods.forEach(item ->
                listFood.put(item.getName(),EntityProcessor.foodEntityToFood(item)));

        return listFood;
    }
}
