package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User implements Serializable{
    public UserInfo userInfo;
    public Fridge fridge;
    public String login;
    public String password;
    public HashMap<String, Recipe> recipes;
}
