package ftc.shift.sample.api;


import ftc.shift.sample.models.*;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import ftc.shift.sample.services.Interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

@RestController
public class RecipeController {


    private static final String RECIPE_PATH = Resources.API_PREFIX + "recipes";

    @Autowired
    private RecipeServiceInterface recipeService;
    @Autowired
    private FridgeServiceInterface serviceFridge;

//    @GetMapping(RECIPE_PATH+"/processState")
//    public @ResponseBody
//    BaseResponse<Recipe> getActiveRecipe(final HttpServletRequest request){
//        BaseResponse<Recipe> response = new BaseResponse<>();
//        response.setStatus("NO");
//        String login = request.getHeader("Login");
//        for(Recipe recipe :service.getAllMyRecipes(login).values()){
//            if(recipe.getIsInProcess()){
//                response.setStatus("OK");
//                response.setData(recipe);
//                break;
//            }
//        }
//        return response;
//
//    }
//
//    @GetMapping(RECIPE_PATH+"/{recipeName}")
//    public @ResponseBody
//    BaseResponse<Recipe> provideRecipe(@PathVariable String recipeName, final HttpServletRequest request) {
//        BaseResponse<Recipe> response = new BaseResponse();
//        Recipe resipe = service.getAllMyRecipes(request.getHeader("Login")).get(recipeName);
//        response.setData(resipe);
//        return response;
//    }
//
//    @GetMapping(RECIPE_PATH)
//    public @ResponseBody
//    BaseResponse<Set<String>> provideAllRecipeNames(final HttpServletRequest request) {
//        BaseResponse<Set<String>> response = new BaseResponse();
//        Set<String> recipeNames = service.getAllMyRecipes(request.getHeader("Login")).keySet();
//        response.setData(recipeNames);
//        return response;
//    }
//
//    @PostMapping(RECIPE_PATH)
//    public @ResponseBody
//    BaseResponse<Collection<Recipe>> addRecipe(@RequestBody Recipe recipe,final HttpServletRequest request) {
//        BaseResponse<Collection<Recipe>> response = new BaseResponse();
//        String login = request.getHeader("Login");
//        recipe.setIdCooker(login);
//        recipe.setIdRecipe(recipe.getName());
//        service.addRecipeToMyRecipes(recipe);
//        Collection<Recipe> list = service.getAllMyRecipes(login).values();
//        response.setData(list);
//        return response;
//
//    }
////
////    @PutMapping(RECIPE_PATH+"/{recipeName}")
////    public @ResponseBody
////    BaseResponse<Recipe> addProductToRecipe(@PathVariable Recipe recipe,@RequestBody Product product,final HttpServletRequest request) {
////        BaseResponse<Recipe> response = new BaseResponse();
////        // System.out.println(product.toString());
////        String login = request.getHeader("Login");
////        Recipe recipe = service.getAllMyRecipes(login).get(recipeName);
////        service.addProductToMyRecipe(recipe,product);
////        response.setData(recipe);
////        return response;
////
////    }
//
////    @DeleteMapping(RECIPE_PATH+"/{recipeName}/{productName}")
////    public @ResponseBody
////    BaseResponse<Recipe> deleteProductFromRecipe(@PathVariable String recipeName,@PathVariable String productName,final HttpServletRequest request) {
////        BaseResponse<Recipe> response = new BaseResponse();
////        // System.out.println(product.toString());
////        String login = request.getHeader("Login");
////        Recipe recipe = service.removeProductFromMyRecipe(recipeName,productName,login);
////        response.setData(recipe);
////        return response;
////
////    }
//
//    @DeleteMapping(RECIPE_PATH+"/{recipeName}")
//    public @ResponseBody
//    BaseResponse<Collection<Recipe>> deleteRecipe(@PathVariable String recipeName,final HttpServletRequest request) {
//        BaseResponse<Collection<Recipe>> response = new BaseResponse();
//        String userName = request.getHeader("Login");
//        service.removeRecipeFromMyRecipes(userName,recipeName);
//        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
//        response.setData(list);
//
//        return response;
//    }
//
//    @PostMapping(RECIPE_PATH+"/accept")
//    public @ResponseBody
//    BaseResponse<Collection<Recipe>> accepUserForRecipe(@RequestBody AcceptDeniedModel acceptDeniedModel, final HttpServletRequest request) {
//        BaseResponse<Collection<Recipe>> response = new BaseResponse();
//        // System.out.println(product.toString());
//        String login = request.getHeader("Login");
//        Product product = serviceFridge.getProductFromFridge(acceptDeniedModel.getUserName(), acceptDeniedModel.getProductName());
//        Recipe recipe = service.getAllMyRecipes(login).get(acceptDeniedModel.getRecipeName());
//        service.addUserToFinalListRecipe(recipe,product, acceptDeniedModel.getUserName());
//        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
//        response.setData(list);
//        return response;
//
//    }
//
//    @PostMapping(RECIPE_PATH+"/deny")
//    public @ResponseBody
//    BaseResponse<Collection<Recipe>> deniedUserForRecipe(@RequestBody AcceptDeniedModel acceptDeniedModel,final HttpServletRequest request) {
//        BaseResponse<Collection<Recipe>> response = new BaseResponse();
//        // System.out.println(product.toString());
//        String login = request.getHeader("Login");
//        Product product = serviceFridge.getProductFromFridge(acceptDeniedModel.getUserName(),acceptDeniedModel.getProductName());
//        Recipe recipe = service.getAllMyRecipes(login).get(acceptDeniedModel.getRecipeName());
//        service.deleteUserFromListForProductIdForRecipeId(recipe,product,acceptDeniedModel.getUserName());
//        Collection<Recipe> list = service.getAllMyRecipes(request.getHeader("Login")).values();
//        response.setData(list);
//        return response;
//
//    }
//
//    @PostMapping(RECIPE_PATH+"/request/accept")
//    public @ResponseBody
//    BaseResponse<Collection<Recipe>> aceptedRecipeForUser(@RequestBody AcceptDeniedModel acceptDeniedModel,final HttpServletRequest request) {
//        BaseResponse<Collection<Recipe>> response = new BaseResponse();
//        // System.out.println(product.toString());
//        String login = request.getHeader("Login");
//        Product product = serviceFridge.getProductFromFridge(login,acceptDeniedModel.getProductName());
//        Recipe recipe = service.getAllMyRecipes(acceptDeniedModel.getUserName()).get(acceptDeniedModel.getRecipeName());
//        service.addRecipeToNotMyRecipes(recipe,login,product);
//        Collection<Recipe> list = service.getAllNotMyRecipes(request.getHeader("Login")).values();
//        response.setData(list);
//        return response;
//
//    }

//    @PostMapping(RECIPE_PATH+"/{userName}/deny")
//    public @ResponseBody
//    BaseResponse<Collection<Recipe>> deniedRecipeForUser(@PathVariable String userName,@RequestBody String productName,@RequestBody String recipeName,final HttpServletRequest request) {
//        BaseResponse<Collection<Recipe>> response = new BaseResponse();
//        // System.out.println(product.toString());
//        String login = request.getHeader("Login");
//        Product product = serviceFridge.getProductFromFridge(login,productName);
//        Recipe recipe = service.getAllMyRecipes(userName).get(recipeName);
//        service.addRecipeToNotMyRecipes(recipe,login,product);
//        Collection<Recipe> list = service.getAllNotMyRecipes(login).values();
//        response.setData(list);
//        return response;
//
//    }






}