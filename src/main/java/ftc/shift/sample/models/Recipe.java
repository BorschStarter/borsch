package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@NoArgsConstructor
@Data
@AllArgsConstructor
//@Builder
public final class Recipe implements Serializable{
    private String idRecipe;
    private String name;
    private String idCooker;
    private Boolean isInProcess;
    private ArrayList<Product> productList = new ArrayList<>();
    private HashMap<String, String> finalUserList = new HashMap<>();
    private HashMap<String,HashMap<String,User>> listUsersForEachProduct = new HashMap<>();
    //HashMap<String,String> продукт-ИдПользователь

}