package ftc.shift.sample.entity;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="food_id")
    private Integer foodId;

    @Column(name="all_weight")
    private Integer allWeight;

    @Column(name="reserved_weight")
    private Integer reservedWeight;
}
