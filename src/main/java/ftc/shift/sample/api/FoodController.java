package ftc.shift.sample.api;

import ftc.shift.sample.Controllers.HeaderProcessor;
import ftc.shift.sample.models.Food;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.UserValidInfo;
import ftc.shift.sample.services.Interfaces.FoodServiceInterface;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import ftc.shift.sample.services.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

import static ftc.shift.sample.api.ExceptionsConst.*;


@RestController
public class  FoodController {

    private static final String FOOD_PATH = Resources.API_PREFIX + "food";

    @Autowired
    private FoodServiceInterface foodService;
    @Autowired
    private Validation validation;

    @GetMapping(FOOD_PATH+"/{nameFood}")
    public @ResponseBody
    BaseResponse<Collection<Food>> provideFoodSearchList(@PathVariable String nameFood,final HttpServletRequest request) {
        BaseResponse<Collection<Food>> response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try {
            switch (validation.checkValidation(userValidInfo, response)) {
                case VALID:
                    Collection<Food> food = foodService.getListFoodStartWith(nameFood);
                    response.setData(food);
                    break;
                case NON_VALID:
                    response.setStatus(NON_VALID_ERROR_STATUS);
                    response.setMessage(NON_VALID_ERROR_MESSAGE);
                    break;
                case ERROR:
                    break;
            }
        }catch (IllegalArgumentException ex){
            response.setStatus(ILLEGAL_ARGUMENT_ERROR_STATUS);
            response.setMessage(ex.getMessage());
        }catch(NullPointerException ex){
            response.setStatus(NULL_POINTER_EXCEPTION_STATUS);
            response.setMessage(NULL_POINTER_EXCEPTION_MESSAGE+"  "+ex.getMessage());
        }catch (Exception ex){
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" provideFoodSearchList"+ex.getMessage());
        }

        return response;
    }

    @PutMapping(FOOD_PATH)
    public @ResponseBody
    BaseResponse createFood(@RequestBody Food food,final HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try {
            switch (validation.checkValidation(userValidInfo, response)) {
                case VALID:
                    foodService.createFood(food);
                    break;
                case NON_VALID:
                    response.setStatus(NON_VALID_ERROR_STATUS);
                    response.setMessage(NON_VALID_ERROR_MESSAGE);
                    break;
                case ERROR:
                    break;
            }
        }catch(NullPointerException ex){
            response.setStatus(NULL_POINTER_EXCEPTION_STATUS);
            response.setMessage(NULL_POINTER_EXCEPTION_MESSAGE+"  "+ex.getMessage());
        }catch (Exception ex){
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" createFood"+ex.getMessage());
        }

        return response;
    }
}
