package ftc.shift.sample.services;

import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FridgeService{

    private final UserRepository userRepository;

    @Autowired
    public FridgeService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Fridge provideUserFridge(String id){
//        return provideUser(id).getFridge();
//    }
//
//    public Fridge addProductInFridge(String id, Product product){
//        addProductToFridge(provideUser(id), product);
//        return provideUserFridge(id);
//    }

//    public List<Food> getFoodSearchList(String foodName){
//
//    }

    public void addProductToFridge(User user, Product product){ //User->userID

        if (user == null || product == null){
            throw new IllegalArgumentException();
        }
        else {
            user.getFridge().getProducts().put(product.getId(),product);
        }
    }

    public void removeProductFromFridge(User user, Product product){ //User->userID product->productID

        if (user == null || product == null || !user.getFridge().getProducts().containsKey(product.getId())){
            throw new IllegalArgumentException();
        }
        else {
            user.getFridge().getProducts().remove(product.getId());
        }
    }

    public Product getProductFromFridge(User user, Product product){ //?
        if (user == null || product == null || !user.getFridge().getProducts().containsKey(product.getId())){
            throw new IllegalArgumentException();
        }
        else {
            return user.getFridge().getProducts().get(product.getId());
        }
    }

}
