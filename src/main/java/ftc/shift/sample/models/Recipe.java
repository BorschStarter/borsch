package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class Recipe implements Serializable{
    private String idRecipe;
    private String name;
    private String idPovar;
    private ArrayList<Product> productList;
    private ArrayList<User> finalUserList;
    private ArrayList<ArrayList<User>> listUsersForEachProduct;
}
