package ftc.shift.sample.api;



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
    private UserServiceInterface service;

    @PostMapping(USERS_PATH+"/registration")
    public @ResponseBody
    BaseResponse createUser(@RequestBody UserLogin userLogin) {
        System.out.println(userLogin.userName);
        System.out.println(userLogin.password);
        BaseResponse response = new BaseResponse();
        service.registration(userLogin);
        return response;
    }

    @PostMapping(USERS_PATH+"/login"+"/{userName}")
    public @ResponseBody
    BaseResponse<UserValidInfo> loginUser(@RequestBody UserLogin userLogin) {
        BaseResponse<UserValidInfo> response = new BaseResponse();
        UserValidInfo token = service.createToken(userLogin);
        response.setData(token);
        return response;
    }

    @GetMapping(USERS_PATH+"/{userName}")
    public @ResponseBody
    BaseResponse<UserInfo> createUser(@PathVariable String userName,final HttpServletRequest request) {
        BaseResponse<UserInfo> response = new BaseResponse();
        UserInfo info = service.provideUserInfo(userName);
        response.setData(info);
        return response;
    }

    @PatchMapping(USERS_PATH)
    public @ResponseBody
    BaseResponse<UserInfo> updateBook(final HttpServletRequest request, @RequestBody UserInfo info) {
        BaseResponse<UserInfo> response = new BaseResponse<>();
        String userName = request.getHeader("Login");
        UserInfo result = service.updateUserInfo(info);
        response.setData(result);
        return response;
    }
    @GetMapping(USERS_PATH+"/logout"+"/{userName}")
    public @ResponseBody
    BaseResponse<UserInfo> logoutUser(@PathVariable String userName,final HttpServletRequest request) {
        BaseResponse<UserInfo> response = new BaseResponse();
        UserValidInfo userValidInfo = new UserValidInfo();
        userValidInfo.setId(userName);
        userValidInfo.setToken(request.getHeader("Token"));
        service.deleteToken(userValidInfo);
        return response;
    }



}


