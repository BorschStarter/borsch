package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Recipe implements Serializable{
    private String id;
    private String name;
    private HashMap<String, Product> productList;
    private HashMap<String, User> userList;
}
