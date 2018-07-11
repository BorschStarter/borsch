package ftc.shift.sample.api;

import ftc.shift.sample.models.Food;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.services.Interfaces.FoodServiceInterface;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;





@RestController
public class  FoodController {

    private static final String FOOD_PATH = Resources.API_PREFIX + "food";

    @Autowired
    private FoodServiceInterface foodService;


    @GetMapping(FOOD_PATH+"/{nameFood}")
    public @ResponseBody
    BaseResponse<Collection<Food>> provideFoodSearchList(@PathVariable String nameFood) {
        BaseResponse<Collection<Food>> response = new BaseResponse();
        ArrayList<Food> arrayList = foodService.getListFoodStartWith(nameFood);
        Collection<Food> food  = arrayList; // этот метод лежит в FoodServiceInterface
        response.setData(food);
        return response;
    }

    @PutMapping(FOOD_PATH)
    public @ResponseBody
    BaseResponse createFood(@RequestBody Food food) {
        BaseResponse response = new BaseResponse();
        foodService.createFood(food);
        return response;
    }
}
