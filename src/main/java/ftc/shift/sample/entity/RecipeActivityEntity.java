package ftc.shift.sample.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@EnableTransactionManagement
@Entity
@Table(name = "recipe_activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="product_id")
    private Integer productId;

    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="state")
    private Integer state;
}
