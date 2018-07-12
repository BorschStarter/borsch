package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Food;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.TreeMap;

public interface FoodServiceInterface {

    //IllegalArgumentException("Продуктов с таким именем нет в базе");
    ArrayList<Food> getListFoodStartWith(@NonNull String startNameOfFood);

    //IllegalArgumentException("Такого food не существует")
    Food provideFood(@NonNull Integer idFood) throws IllegalArgumentException;

    Food updateFood(@NonNull Food food);

    //IllegalArgumentException("Такого food не существовало")
    void deleteFood(@NonNull Integer idFood);

    Food createFood(@NonNull Food food);

}
