package ftc.shift.sample.api;



import ftc.shift.sample.Controllers.HeaderProcessor;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.models.UserValidInfo;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private static final String USERS_PATH = Resources.API_PREFIX + "users";




    @Autowired
    private UserServiceInterface userService;

    @PostMapping(USERS_PATH+"/registration")
    public @ResponseBody
    BaseResponse createUser(@RequestBody UserLogin userLogin) {
        System.out.println(userLogin.userName);
        System.out.println(userLogin.password);
        BaseResponse response = new BaseResponse();
        userService.registration(userLogin);
        return response;
    }

    @PostMapping(USERS_PATH+"/login"+"/{userName}")
    public @ResponseBody
    BaseResponse<UserValidInfo> loginUser(@RequestBody UserLogin userLogin) {
        BaseResponse<UserValidInfo> response = new BaseResponse();
        try{
            UserValidInfo token = userService.logIn(userLogin);
            response.setData(token);
        }catch (IllegalArgumentException ex){
            response.setStatus("1");
            response.setMessage(ex.getMessage());
        }finally {
            response.setStatus("0");
            response.setMessage("Unexpected Error");
        }
        return response;
    }

    @GetMapping(USERS_PATH+"/logout")
    public @ResponseBody
    BaseResponse<UserInfo> logoutUser(final HttpServletRequest request) {
        BaseResponse<UserInfo> response = new BaseResponse();
        UserValidInfo userValidInfo = HeaderProcessor.pullUserValidInfo(request);
        try{
            if(userService.checkAccess(userValidInfo)){
                userService.LogOut(userValidInfo);
            }else{
                response.setStatus("NON_VALID");
                response.setMessage("Данная сессия была завершена.");
            }
        }finally {
            response.setStatus("0");
            response.setMessage("Unexpected Error");
        }

        return response;
    }

    @GetMapping(USERS_PATH+"/{userId}")
    public @ResponseBody
    BaseResponse<UserInfo> createUser(@PathVariable Integer userId,final HttpServletRequest request) {
        BaseResponse<UserInfo> response = new BaseResponse();
        UserInfo info = userService.provideUserInfo(userId);
        response.setData(info);
        return response;
    }

    @PatchMapping(USERS_PATH)
    public @ResponseBody
    BaseResponse<UserInfo> updateBook(final HttpServletRequest request, @RequestBody UserInfo info) {
        BaseResponse<UserInfo> response = new BaseResponse<>();
        UserInfo result = userService.updateUserInfo(info);
        response.setData(result);
        return response;
    }




}


