package ftc.shift.sample.api;



import ftc.shift.sample.Controllers.HeaderProcessor;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.models.UserValidInfo;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import static ftc.shift.sample.api.ValidationStatus.*;

@RestController
public class UserController {

    private static final String USERS_PATH = Resources.API_PREFIX + "users";
    private static final String ILLEGAL_ARGUMENT_ERROR_STATUS="ILLEGAL_ARGUMENT_ERROR";
    private static final String UNEXPECTED_ERROR_STATUS = "UNEXPECTED_ERROR";
    private static final String UNEXPECTED_ERROR_MESSAGE="Unexpected Error in method";
    private static final String NON_VALID_ERROR_STATUS ="NON_VALID_ERROR";
    private static final String NON_VALID_ERROR_MESSAGE ="Данная сессия была завершена." +
            " Повторите авторизацию";
    private static final String NULL_POINTER_EXCEPTION_STATUS ="NULL_POINTER_EXCEPTION";
    private static final String NULL_POINTER_EXCEPTION_MESSAGE = "Тело запроса " +
            "отсутствует или некорректно";

    @Autowired
    private UserServiceInterface userService;

    @PostMapping(USERS_PATH+"/registration")
    public @ResponseBody
    BaseResponse createUser(@RequestBody UserLogin userLogin) {
        BaseResponse response = new BaseResponse();
        try {
            userService.registration(userLogin);
        } catch (IllegalArgumentException ex) {
            response.setStatus(ILLEGAL_ARGUMENT_ERROR_STATUS);
            response.setMessage(ex.getMessage());
        }catch(NullPointerException ex){
            response.setStatus(NULL_POINTER_EXCEPTION_STATUS);
            response.setMessage(NULL_POINTER_EXCEPTION_MESSAGE+"  "+ex.getMessage());
        }finally {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+"logoutUser");
        }
        return response;
    }

    @PostMapping(USERS_PATH+"/login")
    public @ResponseBody
    BaseResponse<UserValidInfo> loginUser(@RequestBody UserLogin userLogin) {
        BaseResponse<UserValidInfo> response = new BaseResponse();
        try{
            UserValidInfo token = userService.logIn(userLogin);
            response.setData(token);
        }catch (IllegalArgumentException ex){
            response.setStatus(ILLEGAL_ARGUMENT_ERROR_STATUS);
            response.setMessage(ex.getMessage());
        }catch(NullPointerException ex){
            response.setStatus(NULL_POINTER_EXCEPTION_STATUS);
            response.setMessage(NULL_POINTER_EXCEPTION_MESSAGE+"  "+ex.getMessage());
        }finally {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            //по хорошему так делать нельзя
            //спросить что хотят здесь видеть Front-end
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+"loginUser");
        }
        return response;
    }

    @GetMapping(USERS_PATH+"/logout")
    public @ResponseBody
    BaseResponse<UserInfo> logoutUser(final HttpServletRequest request) {
        BaseResponse<UserInfo> response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(checkValidation(userValidInfo,response)){
                case VALID:
                    userService.LogOut(userValidInfo);
                    break;
                case NON_VALID:
                    response.setStatus(NON_VALID_ERROR_STATUS);
                    response.setMessage(NON_VALID_ERROR_MESSAGE);
                    break;
                case ERROR:
                    break;
            }
        }finally {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+"logoutUser");
        }

        return response;
    }

    @GetMapping(USERS_PATH+"/{userId}")
    public @ResponseBody
    BaseResponse<UserInfo> fetchUser(@PathVariable Integer userId,final HttpServletRequest request) {
        BaseResponse<UserInfo> response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(checkValidation(userValidInfo,response)){
                case VALID:
                    UserInfo info = userService.provideUserInfo(userId);
                    response.setData(info);
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
        }finally {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+"fetchUser");
    }
        return response;
    }

    @PatchMapping(USERS_PATH)
    public @ResponseBody
    BaseResponse<UserInfo> updateUser(final HttpServletRequest request, @RequestBody UserInfo info) {
        BaseResponse<UserInfo> response = new BaseResponse<>();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            switch(checkValidation(userValidInfo,response)){
                case VALID:
                    UserInfo userInfo = userService.updateUserInfo(info,request.getIntHeader("id"));
                    response.setData(userInfo);
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
        }finally {
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+"updateUser");
        }
        return response;
    }

    private ValidationStatus checkValidation(@NonNull UserValidInfo userValidInfo, BaseResponse response){
        try{

            if(userService.checkAccess(userValidInfo)){
                return VALID;
            }else{
                return NON_VALID;
            }
        }catch (IllegalArgumentException ex) {
            response.setStatus(ILLEGAL_ARGUMENT_ERROR_STATUS);
            response.setMessage(ex.getMessage());
            return ERROR;
        }catch (Exception ex){
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" "+ex.getMessage());
            return ERROR;
        }
    }



}


