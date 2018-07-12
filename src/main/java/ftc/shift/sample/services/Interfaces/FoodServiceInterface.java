package ftc.shift.sample.services.Interfaces;

import ftc.shift.sample.models.Food;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public interface FoodServiceInterface {

    ArrayList<Food> getListFoodStartWith(@NonNull String startNameOfFood)throws IllegalArgumentException;

    Food provideFood(@NonNull Integer idFood) throws IllegalArgumentException;

    Food updateFood(@NonNull Food food) throws IllegalArgumentException;

    void deleteFood(@NonNull Integer idFood) throws IllegalArgumentException;

    Food createFood(@NonNull Food food);

    TreeMap<String,Food> provideFoods() throws NoSuchElementException;

}
