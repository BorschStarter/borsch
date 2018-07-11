package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.UserEntity;
import ftc.shift.sample.models.*;
import ftc.shift.sample.repositories.interfaces.UserRepository;
import ftc.shift.sample.repositories.interfaces.UserRepositoryEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private TreeMap<String, ftc.shift.sample.models.User> userCache = new TreeMap<>();

    public InMemoryUserRepository(){

//Pavel Durov

        User user = new User();
        UserInfo userInfo = new UserInfo();
        Fridge fridge = new Fridge();
        ArrayList<Product> arrayList = new ArrayList<>();
        arrayList.add(new Product("apple","apple",1.0,0.0));
        arrayList.add(new Product("orange","orange",1.0,0.0));
        arrayList.add(new Product("potato","potato",1.0,0.0));
        user.setRecipeState(new HashMap<String,State>());

        Recipe recipe = new Recipe("Borsch","Borsch","durov",Boolean.FALSE,arrayList,new HashMap<>(),new HashMap<>());
        HashMap<String,Recipe> hashMap =new HashMap<String,Recipe>() ;
        hashMap.put("Borsch", recipe);
        user.setMyRecipes(hashMap);
        user.setFridge(fridge);
        user.setLogin("durov");
        user.setPassword("pavel");
        userInfo.setId("durov");
        userInfo.setCity("Saint-Petersburg");
        userInfo.setCookRate(3);
        userInfo.setDormitory("134");
        userInfo.setEatRate(3);
        userInfo.setEmail("durov@ya.com");
        userInfo.setFirstName("Pavel");
        userInfo.setRoom("567");
        userInfo.setSecondName("Durov");
        userInfo.setTelegram("https://t.me/durov");
        userInfo.setUniversity("NSU");
        userInfo.setVk("https://vk.com/id1");

        user.setUserInfo(userInfo);

        userCache.put(user.getLogin(),user);

    //
    }

    @Autowired
    private UserRepositoryEntity repository;

    @Override
    public UserInfo fetchUserInfo(@NonNull final String idUser){
        UserEntity userEntity = repository.findOne(0);
        UserInfo userInfo = userEntity.toUserInfo();
        return userInfo;
    }
    @Override
    public User fetchUser(@NonNull final String idUser) {
//        User user = new ftc.shift.sample.models.User();
//        user.setUserInfo(fetchUserInfo(idUser));
        return userCache.get(idUser);
    }


    @Override
    public User updateUser(@NonNull final ftc.shift.sample.models.User user) {
        userCache.put(user.getLogin(), user);
        return user;
    }

    @Override
    public void deleteUser(@NonNull final String iduser) {
        userCache.remove(iduser);
    }

    @Override
    public User createUser(@NonNull final ftc.shift.sample.models.User user) {
        userCache.put(user.getLogin(), user);
        return user;
    }

    @Override
    public TreeMap<String, ftc.shift.sample.models.User> getAllUsers() {
        return userCache;
    }
}
