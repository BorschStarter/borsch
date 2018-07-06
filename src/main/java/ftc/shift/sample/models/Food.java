package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.TreeMap;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Food implements Serializable {

    private String id;
    private String name;
    private String category;

    public static TreeMap<Integer,Food> allFood;

    private Food(String name, String category){

        setName(name);
        setCategory(category);
        setId(hashCode());
    }

    static Food addFood(String name, String category){
        if (name == null || category == null)
            throw new IllegalArgumentException();

        Food food = new Food(name, category);
        allFood.put(food.getId(),food);
        return food;
    }

    public Food getFood(Integer id){
        if (allFood.containsKey(id))
            return allFood.get(id);
        else throw new IllegalArgumentException();
    }

    static void removeFood(Food food){
        if (food == null)
            throw new IllegalArgumentException();

        allFood.remove(food.getId());
    }
}
