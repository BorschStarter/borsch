package ftc.shift.sample.models;


import ftc.shift.sample.Enums.RecipeStatement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RecipeInfo {
    private Integer recipeId;
    private String recipeName;
    private Integer userId;
    private Boolean recipeStatement; //active and nonActive
    private ArrayList<Integer> idProducts;
}
