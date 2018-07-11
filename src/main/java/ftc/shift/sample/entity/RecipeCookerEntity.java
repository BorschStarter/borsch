package ftc.shift.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@EnableTransactionManagement
@Entity
@Table(name = "recipe_cooker")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCookerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="user_id")
    private Integer userId;


}
