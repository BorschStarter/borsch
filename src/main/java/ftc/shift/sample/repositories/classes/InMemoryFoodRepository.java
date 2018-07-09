package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.repositories.interfaces.FoodRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFoodRepository implements FoodRepository {

    private TreeMap<String, Food> foodCache = new TreeMap<>();

    public InMemoryFoodRepository(){
        foodCache.put("apple", new Food("1","apple","1"));
        foodCache.put("orange", new Food("2","orange","1"));
        foodCache.put("potato", new Food("3","potato","1"));
        foodCache.put("tomato", new Food("4","tomato","1"));
        foodCache.put("meat", new Food("5","meat","1"));
        foodCache.put("chicken", new Food("6","chicken","1"));
        foodCache.put("pig", new Food("7","pig","1"));
        foodCache.put("flour", new Food("8","flour","1"));
        foodCache.put("salmon", new Food("9","salmon","1"));
        foodCache.put("oil", new Food("10","oil","1"));
        foodCache.put("milk", new Food("11","milk","1"));
        foodCache.put("water", new Food("12","water","1"));
        foodCache.put("sugar", new Food("13","sugar","1"));
        foodCache.put("salt", new Food("14","salt","1"));
        foodCache.put("egg", new Food("15","egg","1"));
        foodCache.put("banana", new Food("16","banana","1"));
        foodCache.put("bread", new Food("17","bread","1"));
        foodCache.put("cherry", new Food("18","cherry","1"));
        foodCache.put("cabbage", new Food("19","cabbage","1"));
        foodCache.put("pepper", new Food("20","pepper","1"));
        foodCache.put("carrot", new Food("21","carrot","1"));
        foodCache.put("onion", new Food("22","onion","1"));
        foodCache.put("garlic", new Food("23","garlic","1"));
        foodCache.put("bay leaf", new Food("24","bay leaf","1"));
        foodCache.put("champignons", new Food("25","champignons","1"));
        foodCache.put("dill", new Food("26","dill","1"));
        foodCache.put("parsley", new Food("27","parsley","1"));
        foodCache.put("cheese", new Food("28","cheese","1"));
        foodCache.put("beer", new Food("29","beer","1"));
        foodCache.put("wine", new Food("30","wine","1"));
    }

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
