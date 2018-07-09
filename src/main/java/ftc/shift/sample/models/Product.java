package ftc.shift.sample.models;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public final class Product {
    private String id;
    private String foodName;
    private Double allWeight;
    private Double reservedWeight;
}
