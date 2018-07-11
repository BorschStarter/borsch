package ftc.shift.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@EnableTransactionManagement
@Entity
@Table(name = "user_for-each_product_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForEachProductListEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="product_id")
    private Integer productId;

    @Column(name="recipe_id")
    private Integer recipeId;

}
