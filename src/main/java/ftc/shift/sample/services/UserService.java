package ftc.shift.sample.services;

import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.User;

import ftc.shift.sample.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User provideUser(String id) {

        if (userRepository.getAllUsers().containsKey(id))
            return userRepository.fetchUser(id);
        else throw new IllegalArgumentException();
    }

    public User updateUser(User user) {

        if (user == null){
            throw new IllegalArgumentException();
        }
        else {
            userRepository.updateUser(user);
            return user;
        }
    }

    public void deleteUser(String id) {
        if (userRepository.getAllUsers().containsKey(id)) {
            userRepository.deleteUser(id);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        else {
            userRepository.createUser(user);
            return user;
        }
    }

    public void addProductToFridge(User user, Product product){

        if (user == null || product == null){
            throw new IllegalArgumentException();
        }
        else {
            user.getFridge().add(product);
        }
    }

    public void removeProductFromFridge(User user, Product product){

        if (user == null || product == null){
            throw new IllegalArgumentException();
        }
        else {
            user.getFridge().remove(product);
        }
    }

    public  Product getProductFromFridge(User user, Product product){
        if (user == null || product == null){
            throw new IllegalArgumentException();
        }
        else {
            return user.fridge.get(product.getId());
        }
    }
}
