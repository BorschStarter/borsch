package ftc.shift.sample.models;

import lombok.*;

@Data
@AllArgsConstructor
public class Product {
    private String id;
    private String foodId;
    private Double allWeight;
    private Double reservedWeight;
}
