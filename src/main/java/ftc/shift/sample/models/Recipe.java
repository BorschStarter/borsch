package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@NoArgsConstructor
@Data
@AllArgsConstructor
public final class Recipe implements Serializable{
    private String idRecipe;
    private String name;
    private String idPovar;
    private ArrayList<Product> productList = new ArrayList<>();
    private HashMap<String, String> finalUserList = new HashMap<>();
    private HashMap<String,HashMap<String,User>> listUsersForEachProduct = new HashMap<>();
}