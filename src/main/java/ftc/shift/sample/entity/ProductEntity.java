package ftc.shift.sample.entity;

import ftc.shift.sample.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@EnableTransactionManagement
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

//    @Autowired
//    private FoodRepositoryEntity foodRepositoryEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="food_id")
    private Integer foodId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="all_weight")
    private Integer allWeight;

    @Column(name="reserved_weight")
    private Integer reservedWeight;


    public Product toProduct(){
        Product product = new Product();
        product.setId(this.getId());
        //product.setFoodName(foodRepositoryEntity.findOne(this.getFoodId()).getName());
        product.setAllWeight(this.getAllWeight());
        product.setReservedWeight(this.getReservedWeight());
        product.setFoodId(this.getFoodId());
        product.setUserId(this.getUserId());
        return product;
    }
}
