package ftc.shift.sample.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeActivity {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer recipeId;
    private Boolean state;
}
