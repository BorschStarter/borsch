package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User implements Serializable{
    private UserInfo userInfo;
    private Fridge fridge;
    private String login;
    private String password;
    private HashMap<String, Recipe> recipes; // я участвствую как повар
    private HashMap<String, State> recipeState; // состояния рецептов, где я учавствую, но не повар
}
