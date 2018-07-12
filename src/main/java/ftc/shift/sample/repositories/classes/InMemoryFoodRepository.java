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
    public FoodEntity fetchFood(@NonNull final Integer idFood) {
        return service.findOne(idFood);
    }

    @Override
    public FoodEntity updateFood(@NonNull final FoodEntity foodEntity) {
        service.delete(foodEntity.getId());
        return service.save(foodEntity);
    }

    @Override
    public void deleteFood(@NonNull final Integer idFood) {
        service.delete(idFood);
    }

    @Override
    public FoodEntity createFood(@NonNull final FoodEntity foodEntity) {
        return (service.save(foodEntity));
    }


    //todo Выгрузить преобразование Entity в модель на сервис (быстроодейстие?)
    @Override
    public TreeMap<String,Food> getAllFoods() {
        Iterable<FoodEntity> foods = service.findAll();
        TreeMap<String,Food> listFood = new TreeMap<>();
        foods.forEach(item ->
                listFood.put(item.getName(),EntityProcessor.foodEntityToFood(item)));

        return listFood;
    }
}
