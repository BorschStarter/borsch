package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class User implements Serializable{
    private UserInfo userInfo;
    private Fridge fridge;
    private String login;
    private String password;
    private HashMap<String,Recipe> myRecipes; // я участвствую как повар
    private HashMap<String, State> recipeState; // состояния рецептов, где я учавствую, но не повар
    private HashMap<String,Recipe> notMyRecipes; // я участвствую как не повар
}
