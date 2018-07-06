//package ftc.shift.sample.api;
//
//
//
//import ftc.shift.sample.models.UserLogin;
//import ftc.shift.sample.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//public class UserController {
//
//    private static final String USERS_PATH = Resources.API_PREFIX + "users";
//
//    @Autowired
//    private UserService service;
//
//    @PostMapping(USERS_PATH+"/registration")
//    public @ResponseBody
//    BaseResponse createUser(@RequestBody UserLogin userLogin) {
//        BaseResponse response = new BaseResponse();
//        try {
//            service.registration(userLogin);
//        }catch (IllegalArgumentException e){
//            response.setStatus("Fault");
//            response.setMessage("Логин занят");
//        }
//        return response;
//    }
//
//    @PostMapping(USERS_PATH)
//    public @ResponseBody
//    BaseResponse<To> loginUser(@RequestBody UserLogin userLogin) {
//        BaseResponse response = new BaseResponse();
//        try {
//            service.registration(userLogin);
//        }catch (IllegalArgumentException e){
//            response.setStatus("Fault");
//            response.setMessage("Логин занят");
//        }
//        return response;
//    }
//}
//
