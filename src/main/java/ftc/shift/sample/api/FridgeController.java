package ftc.shift.sample.api;


import ftc.shift.sample.Controllers.HeaderProcessor;
import ftc.shift.sample.models.*;
import ftc.shift.sample.services.Interfaces.FridgeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ftc.shift.sample.services.Validation;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static ftc.shift.sample.api.ExceptionsConst.*;
import static ftc.shift.sample.api.Resources.FRIDGE_PATH;

@RestController
public class FridgeController {


    @Autowired
    private FridgeServiceInterface fridgeService;
    @Autowired
    private Validation validation;

        @GetMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<Collection<Product>> provideFridgeInfo(final HttpServletRequest request) {
            BaseResponse<Collection<Product>> response = new BaseResponse();
            UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
            try{
                switch(validation.checkValidation(userValidInfo,response)){
                    case VALID:
                        Collection<Product> products = fridgeService
                            .provideFridge(userValidInfo.getIdUser()).getProducts();
                        response.setData(products);
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

        @PostMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<Collection<Product>> addProductToFriedge(@RequestBody Product product,final HttpServletRequest request) {
            BaseResponse<Collection<Product>> response = new BaseResponse();
            UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
            try{
                switch(validation.checkValidation(userValidInfo,response)){
                    case VALID:
                        fridgeService.addProductInFridge(userValidInfo.getIdUser(),product);
                        Collection<Product> products = fridgeService
                                .provideFridge(userValidInfo.getIdUser()).getProducts();
                        response.setData(products);
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
    @DeleteMapping(FRIDGE_PATH)
    public @ResponseBody
    BaseResponse<Collection<Product>> removeProductToFriedge(@RequestBody Integer Idproduct,final HttpServletRequest request) {
        BaseResponse<Collection<Product>> response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(validation.checkValidation(userValidInfo,response)){
                case VALID:
                    fridgeService.removeProductFromFridge(userValidInfo.getIdUser(),Idproduct);
                    Collection<Product> products = fridgeService
                            .provideFridge(userValidInfo.getIdUser()).getProducts();
                    response.setData(products);
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

}
