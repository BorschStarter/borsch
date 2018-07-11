package ftc.shift.sample.entity;

import ftc.shift.sample.models.Food;
import lombok.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;


@EnableTransactionManagement
@Entity
@Table(name = "food")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name", columnDefinition = "text")
    private String name;

    @Column(name="category", columnDefinition = "text")
    private String category;

    public Food toFood(){
        Food food =new Food();
        food.setId(this.getId());
        food.setName(this.getName());
        food.setCategory(this.getCategory());
        return food;
    }


}
