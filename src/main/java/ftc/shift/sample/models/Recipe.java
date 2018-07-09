package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class Recipe implements Serializable{
    private String idRecipe;
    private String name;
    private String idPovar;
    private ArrayList<Product> productList;
    private HashMap<String, String> finalUserList;
    private HashMap<String,HashMap<String,User>> listUsersForEachProduct;
}