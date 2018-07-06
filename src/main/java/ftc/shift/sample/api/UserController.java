package ftc.shift.sample.api;



import ftc.shift.sample.models.Token;
import ftc.shift.sample.models.UserInfo;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import ftc.shift.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private static final String USERS_PATH = Resources.API_PREFIX + "users";

    @Autowired
    private UserServiceInterface service;

//    @PostMapping(USERS_PATH+"/registration")
//    public @ResponseBody
//    BaseResponse createUser(@RequestBody UserLogin userLogin) {
//        BaseResponse response = new BaseResponse();
//        service.registration(userLogin);
//        return response;
//    }

//    @PostMapping(USERS_PATH+"/{userName}")
//    public @ResponseBody
//    BaseResponse<Token> loginUser(@RequestBody UserLogin userLogin) {
//        BaseResponse<Token> response = new BaseResponse();
//        Token token = service.createToken(userLogin);
//        response.setData(token);
//        return response;
//    }

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
        UserInfo result = service.updateUserInfo(userName,info);
        response.setData(result);
        return response;
    }
}


