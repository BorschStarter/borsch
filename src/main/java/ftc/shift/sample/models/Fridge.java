package ftc.shift.sample.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@NoArgsConstructor
public final class Fridge implements Serializable{
    private ArrayList<Product> products;
}
