package ftc.shift.sample.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@NoArgsConstructor
public final class Fridge implements Serializable{
    private HashMap<String, Product> products = new HashMap<>();
}
