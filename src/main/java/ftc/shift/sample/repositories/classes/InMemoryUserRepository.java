package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.entity.LoginEntity;
import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.entity.UserInfoEntity;
import ftc.shift.sample.models.*;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.FoodRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.UserRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {

//    private TreeMap<String, ftc.shift.sample.models.User> userCache = new TreeMap<>();

    public InMemoryUserRepository(){

//Pavel Durov

//        User user = new User();
//        UserInfo userInfo = new UserInfo();
//        Fridge fridge = new Fridge();
//        ArrayList<Product> arrayList = new ArrayList<>();
//        arrayList.add(new Product("apple","apple",1.0,0.0));
//        arrayList.add(new Product("orange","orange",1.0,0.0));
//        arrayList.add(new Product("potato","potato",1.0,0.0));
//        user.setRecipeState(new HashMap<String,State>());

//        Recipe recipe = new Recipe("Borsch","Borsch","durov",Boolean.FALSE,arrayList,new HashMap<>(),new HashMap<>());
//        HashMap<String,Recipe> hashMap =new HashMap<String,Recipe>() ;
//        hashMap.put("Borsch", recipe);
//        user.setMyRecipes(hashMap);
//        user.setFridge(fridge);
//        user.setLogin("durov");
//        user.setPassword("pavel");
//        userInfo.setId("durov");
//        userInfo.setCity("Saint-Petersburg");
//        userInfo.setCookRate(3);
//        userInfo.setDormitory("134");
//        userInfo.setEatRate(3);
//        userInfo.setEmail("durov@ya.com");
//        userInfo.setFirstName("Pavel");
//        userInfo.setRoom("567");
//        userInfo.setSecondName("Durov");
//        userInfo.setTelegram("https://t.me/durov");
//        userInfo.setUniversity("NSU");
//        userInfo.setVk("https://vk.com/id1");

//        user.setUserInfo(userInfo);

//        userCache.put(user.getLogin(),user);

    //
    }

    @Autowired
    private UserRepositoryEntity repository;
    @Autowired
    private LoginRepositoryEntity loginRepository;
    @Autowired
    private FridgeRepositoryEntity fridgeRepository;
    @Autowired
    private ProductRepositoryEntity productRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodRepositoryEntity foodRepositoryEntity;

    public Integer getFoodId(String foodName){
        for (Food food: foodRepository.getAllFoods().values()){
            if(food.getName().equals(foodName)){
                return food.getId();
            }
        }
        return null;
    }

    @Override
    public User fetchUser(@NonNull final Integer idUser){
        UserInfoEntity userInfoEntity = repository.findOne(idUser);
        UserInfo userInfo = EntityProcessor.UserInfoEntityToUserInfo(userInfoEntity);
        User user = new User();
        user.setUserInfo(userInfo);
        LoginEntity login = provideUserLoginInfo(idUser);
        user.setPassword(login.getPassword());
        user.setLogin(login.getLogin());
        Fridge fridge = new Fridge();
        HashMap<String,Product> map = new HashMap<>();
        for (ProductEntity productEntity : productRepository.findAll()){
           if(productEntity.getUserId().equals(idUser)){
              Product product = productEntity.toProduct();//productRepository.findOne(fridgeEntity.getProductId()).toProduct();
               product.setFoodName(foodRepositoryEntity.findOne(product.getFoodId()).getName());
                map.put(product.getFoodName(),product);
           }
        }
        fridge.setProducts(map);
        user.setFridge(fridge);
        return user;
    }

    @Override
    public Boolean checkInitLogin(String login) {
        return null;
    }

    @Override
    public Boolean checkLoginInformation(UserLogin userLogin) {
        return null;
    }

    @Override
    public User updateUser(@NonNull  User user) {
        UserInfo userInfo =EntityProcessor.UserInfoEntityToUserInfo(
                repository
                        .save(EntityProcessor
                                .UserInfoToUserInfoEntity(
                                        user
                                                .getUserInfo()
                                )));

        for(ProductEntity productEntity: productRepository.findAll()){
            if(productEntity.getUserId().equals(user.getUserInfo().getId()));
            productRepository.delete(productEntity.getId());
        }
        for(Product product : user.getFridge().getProducts().values()){
            productRepository.save(product.toProductEntity());
        }


        return fetchUser(user.getUserInfo().getId());

    }

    @Override
    public void deleteUser(@NonNull final Integer iduser) {
        repository.delete(iduser);
    }

    @Override
    public User createUser(@NonNull final User user) {
        user.setUserInfo(EntityProcessor.UserInfoEntityToUserInfo(
                repository.save(EntityProcessor
                        .UserInfoToUserInfoEntity(
                                user.getUserInfo()))));

        LoginEntity login = new LoginEntity();
        login.setLogin(user.getLogin());
        login.setPassword(user.getPassword());
        login.setUserId(user.getUserInfo().getId());
        login=loginRepository.save(login);
        user.setLogin(login.getLogin());
        user.setPassword(login.getPassword());
        return user;
    }

    public LoginEntity provideUserLoginInfo(Integer idUser){
        Iterable<LoginEntity> list = loginRepository.findAll();
        for(LoginEntity login :list){
            if(login.getUserId().equals(idUser)){
                return login;
            }
        }
        return null;
    }

    @Override
    public TreeMap<String, User> getAllUsers() {
        TreeMap<String,User> map = new TreeMap<>();
        Iterable<LoginEntity> list = loginRepository.findAll();

        for(LoginEntity loginEntity : list){
            User user = fetchUser(loginEntity.getUserId());
            map.put(loginEntity.getLogin(),user);
        }
        return  map;
    }


}
