package ftc.shift.sample.api;


import ftc.shift.sample.models.*;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import ftc.shift.sample.services.Interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

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
        Collection<Recipe> list = service.getAllMyRecipes(login).values();
        response.setData(list);
        return response;

    }
    @GetMapping(RECIPE_PATH+"/delete/{recipeName}")
    public @ResponseBody
    BaseResponse<Collection<Recipe>> deleteRecipe(@PathVariable String recipeName,final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        String userName = request.getHeader("Login");
        service.removeRecipeFromMyRecipes(userName,recipeName);
        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
        response.setData(list);

        return response;
    }

    @PostMapping(RECIPE_PATH+"/accept")
    public @ResponseBody
    BaseResponse<Collection<Recipe>> accepUserForRecipe(@RequestBody AcceptModel acceptModel, final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        // System.out.println(product.toString());
        String login = request.getHeader("Login");
        Product product = serviceFridge.getProductFromFridge(acceptModel.getUserName(),acceptModel.getProductName());
        Recipe recipe = service.getAllMyRecipes(login).get(acceptModel.getRecipeName());
        service.addUserToFinalListRecipe(recipe,product,acceptModel.getUserName());
        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
        response.setData(list);
        return response;

    }
    @PostMapping(RECIPE_PATH+"/denied")
    public @ResponseBody
    BaseResponse<Collection<Recipe>> deniedUserForRecipe(@RequestBody String userName,@RequestBody String productName,@RequestBody String recipeName,final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        // System.out.println(product.toString());
        String login = request.getHeader("Login");
        Product product = serviceFridge.getProductFromFridge(userName,productName);
        Recipe recipe = service.getAllMyRecipes(login).get(recipeName);
        service.deleteUserFromListForProductIdForRecipeId(recipe,product,userName);
        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
        response.setData(list);
        return response;

    }

    @PostMapping(RECIPE_PATH+"/{userName}/accepted")
    public @ResponseBody
    BaseResponse<Collection<Recipe>> aceptedRecipeForUser(@PathVariable String userName,@RequestBody String productName,@RequestBody String recipeName,final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        // System.out.println(product.toString());
        String login = request.getHeader("Login");
        Product product = serviceFridge.getProductFromFridge(login,productName);
        Recipe recipe = service.getAllMyRecipes(userName).get(recipeName);
        service.addRecipeToNotMyRecipes(recipe,login,product);
        Collection<Recipe> list = service.getAllNotMyRecipes(request.getHeader("Login")).values();
        response.setData(list);
        return response;

    }
    @PostMapping(RECIPE_PATH+"/{userName}/denied")
    public @ResponseBody
    BaseResponse<Collection<Recipe>> deniedRecipeForUser(@PathVariable String userName,@RequestBody String productName,@RequestBody String recipeName,final HttpServletRequest request) {
        BaseResponse<Collection<Recipe>> response = new BaseResponse();
        // System.out.println(product.toString());
        String login = request.getHeader("Login");
        Product product = serviceFridge.getProductFromFridge(login,productName);
        Recipe recipe = service.getAllMyRecipes(userName).get(recipeName);
        service.addRecipeToNotMyRecipes(recipe,login,product);
        Collection<Recipe> list = service.getAllNotMyRecipes(login).values();
        response.setData(list);
        return response;

    }



}