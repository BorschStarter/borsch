package ftc.shift.sample.api;


import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.services.Interfaces.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FridgeController {


        private static final String FRIDGE_PATH = Resources.API_PREFIX + "fridge";

        @Autowired
        private FridgeService service;

        @PostMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse createUser(@RequestBody UserLogin userLogin, final HttpServletRequest request) {
            BaseResponse response = new BaseResponse();

            return response;
        }

        @PostMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<To> loginUser(@RequestBody UserLogin userLogin) {
            BaseResponse response = new BaseResponse();
            try {
                service.registration(userLogin);
            }catch (IllegalArgumentException e){
                response.setStatus("Fault");
                response.setMessage("Логин занят");
            }
            return response;

    }


}
