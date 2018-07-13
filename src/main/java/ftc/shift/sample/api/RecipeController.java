package ftc.shift.sample.api;


import ftc.shift.sample.Controllers.HeaderProcessor;
import ftc.shift.sample.models.*;
import ftc.shift.sample.services.Interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ftc.shift.sample.services.Validation;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

import static ftc.shift.sample.api.ExceptionsConst.*;
import static ftc.shift.sample.api.Resources.RECIPE_PATH;


@RestController
public class RecipeController {

    @Autowired
    private RecipeServiceInterface recipeService;
    @Autowired
    private Validation validation;

    @GetMapping(RECIPE_PATH+"/processState")
    public @ResponseBody
    BaseResponse<RecipeInfo> getActiveRecipe(final HttpServletRequest request){
        BaseResponse<RecipeInfo> response = new BaseResponse<>();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    RecipeInfo recipeInfo = recipeService
                            .fetchActiveRecipe(userValidInfo.getIdUser());
                    response.setData(recipeInfo);
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
        }catch(Exception ex) {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" fetchUser "+ex.getMessage());
        }
        return response;

    }

    @GetMapping(RECIPE_PATH+"/{recipeId}")
    public @ResponseBody
    BaseResponse<RecipeInfo> provideRecipe(@PathVariable Integer recipeId, final HttpServletRequest request) {
        BaseResponse<RecipeInfo> response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    RecipeInfo recipeInfo = recipeService.fetchRecipe(recipeId);
                    response.setData(recipeInfo);
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
        }catch(Exception ex) {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" fetchUser "+ex.getMessage());
        }
        return response;
    }

    @GetMapping(RECIPE_PATH)
    public @ResponseBody
    BaseResponse<Collection<RecipeInfo>> provideAllRecipeNames(final HttpServletRequest request) {
        BaseResponse<Collection<RecipeInfo>> response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    Collection<RecipeInfo> collectionOfRecipeInfo = recipeService
                            .fetchAllRecipes(userValidInfo.getIdUser());
                    response.setData(collectionOfRecipeInfo);
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
        }catch(Exception ex) {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" fetchUser "+ex.getMessage());
        }
        return response;
    }

    @PostMapping(RECIPE_PATH+"/{recipeName}")
    public @ResponseBody
    BaseResponse addRecipe(@PathVariable String recipeName,
                           @RequestBody Product product, final HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    recipeService.createRecipe(userValidInfo.getIdUser(),recipeName,product);
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
        }catch(Exception ex) {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" fetchUser "+ex.getMessage());
        }
        return response;

    }

    @PutMapping(RECIPE_PATH+"/{recipeId}")
    public @ResponseBody
    BaseResponse addProductToRecipe(@PathVariable Integer recipeId,@RequestBody Product product,final HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    recipeService.addProductToRecipe(recipeId,product,userValidInfo.getIdUser());
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
        }catch(Exception ex) {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" fetchUser "+ex.getMessage());
        }
        return response;

    }

    @DeleteMapping(RECIPE_PATH+"/{recipeId}/{productId}")
    public @ResponseBody
    BaseResponse deleteProductFromRecipe(@PathVariable Integer recipeId,@PathVariable Integer productId,final HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    recipeService.removeProductFromRecipe(recipeId,productId,userValidInfo.getIdUser());
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
        }catch(Exception ex) {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" fetchUser "+ex.getMessage());
        }
        return response;

    }

    @DeleteMapping(RECIPE_PATH+"/{recipeId}")
    public @ResponseBody
    BaseResponse deleteRecipe(@PathVariable Integer recipeId,final HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    recipeService.removeRecipe(recipeId,userValidInfo.getIdUser());
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
        }catch(Exception ex) {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" fetchUser "+ex.getMessage());
        }

        return response;
    }

    @PatchMapping(RECIPE_PATH+"/product")
    public @ResponseBody
    BaseResponse<Product> getProducts(@RequestBody Product product,final HttpServletRequest request){
        BaseResponse<Product>  response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    System.out.println(product.getId());
                    Product resProduct = recipeService.fetchProducts(product.getId());
                    response.setData(resProduct);
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
        }catch(Exception ex) {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" fetchUser "+ex.getMessage());
        }

        return response;
    }


    //
//    @PostMapping(RECIPE_PATH+"/accept")
//    public @ResponseBody
//    BaseResponse<Collection<Recipe>> accepUserForRecipe(@RequestBody AcceptDeniedModel acceptDeniedModel, final HttpServletRequest request) {
//        BaseResponse<Collection<Recipe>> response = new BaseResponse();
//        // System.out.pr intln(product.toString());
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