package ftc.shift.sample.api;


import ftc.shift.sample.models.Food;
import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.Recipe;
import ftc.shift.sample.services.FridgeService;
import ftc.shift.sample.services.Interfaces.FoodServiceInterface;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import ftc.shift.sample.services.Interfaces.RecipeServiceInterface;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class RecipeController {


    private static final String RECIPE_PATH = Resources.API_PREFIX + "recipe";

    @Autowired
    private RecipeServiceInterface service;
    @Autowired
    private FridgeServiceInterface serviceFridge;

    @GetMapping(RECIPE_PATH)
    public @ResponseBody
    BaseResponse<Collection<Recipe>> provideRecipe(final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
        response.setData(list);

        return response;
    }

    @PostMapping(RECIPE_PATH)
    public @ResponseBody
    BaseResponse<Collection<Recipe>> addRecipe(@RequestBody Recipe recipe,final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        // System.out.println(product.toString());
        String login = request.getHeader("Login");
        service.addRecipeToMyRecipes(recipe);
        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
        response.setData(list);
        return response;

    }
    @GetMapping(RECIPE_PATH+"/delete")
    public @ResponseBody
    BaseResponse<Collection<Recipe>> deleteRecipe(@RequestBody String recipe,final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        String userName = request.getHeader("userName");
        service.removeRecipeFromMyRecipes(userName,recipe);
        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
        response.setData(list);

        return response;
    }

    @PostMapping(RECIPE_PATH+"/accept")
    public @ResponseBody
    BaseResponse<Collection<Recipe>> accepUserForRecipe(@RequestBody String userName,@RequestBody String productName,@RequestBody String recipeName,final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        // System.out.println(product.toString());
        String login = request.getHeader("Login");
        Product product = serviceFridge.getProductFromFridge(login,productName);
        Recipe recipe = service.getAllMyRecipes("login").get(recipeName);
        service.addUserToFinalListRecipe(recipe,product,userName);
        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
        response.setData(list);
        return response;

    }



}