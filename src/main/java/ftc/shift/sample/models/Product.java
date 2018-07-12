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
    private Integer userId;
    private Integer foodId;
    private String foodName;
    private Integer allWeight;
    private Integer reservedWeight;

    public ProductEntity toProductEntity(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(this.getId());
        productEntity.setFoodId(this.getFoodId());
        productEntity.setAllWeight(this.getAllWeight());
        productEntity.setReservedWeight(this.getReservedWeight());
        productEntity.setUserId(this.getUserId());
        return productEntity;

    }
}
