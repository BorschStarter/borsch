package ftc.shift.sample.models;

import ftc.shift.sample.entity.FoodEntity;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class Food implements Serializable {

    private Integer id;
    private String name;
    private String category;

    public FoodEntity toFoodEntity(){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setId(this.getId());
        foodEntity.setName(this.getName());
        foodEntity.setCategory(this.getCategory());
        return foodEntity;
    }
}
