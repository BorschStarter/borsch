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

}
