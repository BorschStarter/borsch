package ftc.shift.sample.models;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public final class Product {
    private Integer id;
    private String foodName;
    private Double allWeight;
    private Double reservedWeight;
}
