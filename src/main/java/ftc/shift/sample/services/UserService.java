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
        return userRepository.fetchUser(id);
    }

    public User updateUser(User user) {
        userRepository.updateUser(user);
        return user;
    }

    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }


    public User createUser(User user) {
        userRepository.createUser(user);
        return user;
    }

    public Fridge provideUserFridge(String id){
        User user = userRepository.fetchUser(id);
        return user.getFridge();
    }

    public void addToFridge(Product product,User user){
        Fridge fridge = user.getFridge();
        fridge.add(product);
        user.setFridge(fridge);
    }

    public void removeFromFridge(Product product, User user){
        Fridge fridge = user.getFridge();
        fridge.remove(product);
        user.setFridge(fridge);
    }
}
