package ftc.shift.sample.services;

import ftc.shift.sample.Controllers.EntityProcessor;
import ftc.shift.sample.entity.ProductEntity;
import ftc.shift.sample.entity.RecipeEntity;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.RecipeInfo;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.ProductRepository;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.RecipeRepository;
import ftc.shift.sample.services.Interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.NoSuchElementException;

@Service
public class RecipeService implements RecipeServiceInterface {

    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RecipeService(final RecipeRepository recipeRepository,
                         ProductRepository productRepository) {
        this.recipeRepository = recipeRepository;
        this.productRepository=productRepository;
    }

    @Override
    public Product fetchProducts(Integer productId) {
        return  EntityProcessor.productEntityToProduct(productRepository.fetchProduct(productId));

    }

    @Override
    public ArrayList<RecipeInfo> fetchAllRecipes(Integer idUser) throws NoSuchElementException {
        ArrayList<Integer> listOfIdRecipe = recipeRepository.fetchAllUserRecipe(idUser);
        if(listOfIdRecipe.isEmpty()){
            throw new NoSuchElementException("У данного пользователя нет рецептов");
        }else {
            ArrayList<RecipeInfo> listOfRecipeInfo = new ArrayList<>();
            RecipeInfo recipeInfo;
            ArrayList<RecipeEntity> listOfRecipeEntity;
            for (Integer idRecipe : listOfIdRecipe) {
                listOfRecipeEntity = recipeRepository.fetchUserRecipe(idUser, idRecipe);
                recipeInfo = EntityProcessor.recipeEntitysToRecipeInfo(listOfRecipeEntity);
                listOfRecipeInfo.add(recipeInfo);
            }
            return listOfRecipeInfo;
        }
    }

    @Override
    public RecipeInfo fetchRecipe(Integer recipeId) {
        return EntityProcessor.recipeEntitysToRecipeInfo(recipeRepository.fetchRecipe(recipeId));
    }

    @Override
    public RecipeInfo fetchActiveRecipe(Integer idUser) {
        ArrayList<RecipeEntity> listOfRecipeEntity = recipeRepository.fetchActiveUserRecipe(idUser);
        if(listOfRecipeEntity.isEmpty()){
            throw new NoSuchElementException("У данного пользователя нет активногоо рецепта");
        }else{
            RecipeInfo recipeInfo = EntityProcessor.recipeEntitysToRecipeInfo(listOfRecipeEntity);
            return recipeInfo;
        }
    }

    @Override
    public void createRecipe(Integer idUser, String recipeName, Product product) {
            RecipeInfo recipeInfo = new RecipeInfo();
            recipeInfo.setRecipeName(recipeName);
            product.setUserId(idUser);
            ProductEntity productEntity = EntityProcessor.productToProductEntity(product);
            productEntity= productRepository.createProduct(productEntity);

            recipeInfo.setRecipeStatement(false);
            recipeInfo.setUserId(idUser);
            ArrayList<Integer> productId = new ArrayList<>();
            productId.add(productEntity.getId());
            recipeInfo.setIdProducts(productId);
            ArrayList<RecipeEntity> listOfRecipeEntity=
                    EntityProcessor.recipeInfoToRecipeEntitys(recipeInfo);
            recipeRepository.createRecipe(listOfRecipeEntity);
        }


    @Override
    public void removeRecipe(Integer idRecipe,Integer idUser) {
       if(recipeRepository.fetchUserRecipe(idUser, idRecipe)==null){
            throw new IllegalArgumentException("Рецепт не существует");
        }else{
            recipeRepository.deleteRecipe(idRecipe,idUser);
        }
    }

    @Override
    public void addProductToRecipe(Integer idRecipe, Product product,Integer idUser) {
        ArrayList<RecipeEntity> listOfRecipeEntity =
                recipeRepository.fetchUserRecipe(idUser, idRecipe);
        if(listOfRecipeEntity==null){
            throw new IllegalArgumentException("Рецепт не существует");
        }else if(listOfRecipeEntity.get(0).getRecipeStatement()){
            throw new IllegalArgumentException("Рецепт в данный момент активен");
        }else{
            ProductEntity productEntity = EntityProcessor.productToProductEntity(product);
            productEntity = productRepository.createProduct(productEntity);
            product=EntityProcessor.productEntityToProduct(productEntity);
            RecipeEntity recipeEntity = listOfRecipeEntity.get(0);
            recipeEntity.setProductId(product.getId());
            recipeRepository.addProductInRecipe(recipeEntity);
        }

    }

    @Override
    public void removeProductFromRecipe(Integer idRecipe, Integer idProduct,Integer idUser) {
        ArrayList<RecipeEntity> listOfRecipeEntity =
                recipeRepository.fetchUserRecipe(idUser, idRecipe);
        if(listOfRecipeEntity==null){
            throw new IllegalArgumentException("Рецепт не существует");
        }else if(listOfRecipeEntity.get(0).getRecipeStatement()){
            throw new IllegalArgumentException("Рецепт в данный момент активен");
        }else if(!isRecipeContainProduct(listOfRecipeEntity,idProduct)) {
            throw new IllegalArgumentException("Данного продукта нет в рецепте");
        }else{
            recipeRepository.removeProductFromUserRecipe(idUser,idRecipe,idProduct);
            productRepository.removeProduct(idProduct);
        }
    }

    private boolean isRecipeInfoCorrect (RecipeInfo recipeInfo){
        if(recipeInfo.getRecipeStatement()) return false;
        if(recipeInfo.getRecipeName().equals(""))return false;
        return true;
    }

    private boolean isRecipeContainProduct (ArrayList<RecipeEntity> listOfRecipeEntity,
                                            Integer idProduct ){
        for(RecipeEntity recipeEntity:listOfRecipeEntity){
            if(recipeEntity.getProductId().equals(idProduct)){
                return true;
            }
        }
        return false;
    }
}
