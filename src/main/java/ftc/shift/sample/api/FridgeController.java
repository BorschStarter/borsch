package ftc.shift.sample.api;


import ftc.shift.sample.models.Food;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import ftc.shift.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FridgeController {


        private static final String FRIDGE_PATH = Resources.API_PREFIX + "fridge";
    private static final String FOOD_PATH = Resources.API_PREFIX + "food";

        @Autowired
        private FridgeServiceInterface service;

        @GetMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<Fridge> provideFridgeInfo(final HttpServletRequest request) {
            BaseResponse<Fridge> response = new BaseResponse();
            Fridge fridge =service.provideUserFridge(request.getHeader("Login"));
            response.setData(fridge);

            return response;
        }

        @PostMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<Fridge> addProductToFriedge(@RequestBody Product product,final HttpServletRequest request) {
            BaseResponse<Fridge> response = new BaseResponse();
            Fridge fridge = service.addProductInFridge(request.getHeader("login"),product);
            response.setData(fridge);
            return response;

    }
//        @GetMapping(FOOD_PATH+"/{}")
//        public @ResponseBody
//        BaseResponse<List<Food>> provideFoodSearchList(@PathVariable String nameFood,final HttpServletRequest request) {
//        BaseResponse<List<Food>> response = new BaseResponse();
//        List<Food> food = service.getFoodSearchList(nameFood);
//        response.setData(food);
//        return response;
//        }



}
