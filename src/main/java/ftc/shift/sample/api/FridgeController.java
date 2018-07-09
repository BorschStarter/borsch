package ftc.shift.sample.api;


import ftc.shift.sample.models.Food;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.services.FridgeService;
import ftc.shift.sample.services.Interfaces.FoodServiceInterface;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class FridgeController {


    private static final String FRIDGE_PATH = Resources.API_PREFIX + "fridge";
    private static final String FOOD_PATH = Resources.API_PREFIX + "food";

        @Autowired
        private FridgeServiceInterface service;
        private FoodServiceInterface foodService;

        @GetMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<Collection<Product>> provideFridgeInfo(final HttpServletRequest request) {
            BaseResponse<Collection<Product>> response = new BaseResponse();
            Fridge fridge =service.provideFridge(request.getHeader("Login"));
            Collection<Product> list = fridge.getProducts().values();
            response.setData(list);

            return response;
        }

        @PostMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<Collection<Product>> addProductToFriedge(@RequestBody Product product,final HttpServletRequest request) {
            BaseResponse<Collection<Product>> response = new BaseResponse();
           // System.out.println(product.toString());
            String login = request.getHeader("Login");
            Fridge fridge = service.addProductInFridge(request.getHeader("Login"),product);
            Collection<Product> list = fridge.getProducts().values();
            response.setData(list);
            return response;

    }

}
