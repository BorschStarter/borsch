package ftc.shift.sample.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForEachProductList {
    private Integer id;
    private Integer recipeId;
    private Integer productId;
    private Integer userId;
}
