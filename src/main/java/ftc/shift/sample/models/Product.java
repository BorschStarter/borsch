package ftc.shift.sample.models;

import ftc.shift.sample.entity.ProductEntity;
import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public final class Product {
    private Integer id;
    private Integer foodId;
    private String foodName;
    private Integer allWeight;
    private Integer reservedWeight;

}
