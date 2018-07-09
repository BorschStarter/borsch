package ftc.shift.sample.models;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public final class Product {
    private String id;
    private String foodname;
    private Double allWeight;
    private Double reservedWeight;
}
