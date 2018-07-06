package ftc.shift.sample.services;

import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.interfaces.FridgeRepository;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FridgeRepository fridgeRepository;

    @Autowired
    public UserService(final UserRepository userRepository, FridgeRepository fridgeRepository) {
        this.userRepository = userRepository;
        this.fridgeRepository = fridgeRepository;
    }

    public User provideUser(String id) {

        if (userRepository.getAllUsers().containsKey(id))
            return userRepository.fetchUser(id);
        else throw new IllegalArgumentException();
    }

    public User updateUser(User user) {

        if (user == null)
            throw new IllegalArgumentException();
        userRepository.updateUser(user);
        return user;
    }

    public void deleteUser(String id) {
        if (userRepository.getAllUsers().containsKey(id))
            userRepository.deleteUser(id);
        else throw new IllegalArgumentException();
    }

    public User createUser(User user) {
        if (user == null)
            throw new IllegalArgumentException();
        else userRepository.createUser(user);
        return user;
    }

    public Fridge provideUserFridge(String id){

        if (fridgeRepository.getAllFridge().containsKey(id))
            return fridgeRepository.fetchFridge(id);
        else throw new IllegalArgumentException();
    }

    public Fridge updateFridge(Fridge fridge) {

        if (fridge == null)
            throw new IllegalArgumentException();
        fridgeRepository.updateFridge(fridge);
        return fridge;
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
