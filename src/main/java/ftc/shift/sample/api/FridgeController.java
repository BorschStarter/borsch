package ftc.shift.sample.api;


import ftc.shift.sample.models.Fridge;
import ftc.shift.sample.models.Product;
import ftc.shift.sample.models.UserLogin;
import ftc.shift.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FridgeController {


        private static final String FRIDGE_PATH = Resources.API_PREFIX + "fridge";

        @Autowired
        private UserService service;

        @GetMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<Fridge> createUser(final HttpServletRequest request) {
            BaseResponse<Fridge> response = new BaseResponse();
            response.setData(service.provideUserFridge(request.getHeader("Login")));

            return response;
        }

        @PostMapping(FRIDGE_PATH)
        public @ResponseBody
        BaseResponse<Fridge> loginUser(@RequestBody Product product,final HttpServletRequest request) {
            BaseResponse<Fridge> response = new BaseResponse();
            response.setData(service.addProductInFridge(request.getHeader("login"),product));
            return response;

    }


}
