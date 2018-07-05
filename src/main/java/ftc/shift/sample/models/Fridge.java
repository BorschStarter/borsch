package ftc.shift.sample.models;

import java.io.Serializable;
import java.util.HashMap;

public class Fridge implements Serializable{
    private HashMap<String, Product> products = new HashMap<>();

    public void add(Product product){
        this.products.put(product.getId(),product);
    }

    public void remove(Product product){
        this.products.remove(product.getId());
    }

    public Product get(String id){
        return this.products.get(id);
    }
}
